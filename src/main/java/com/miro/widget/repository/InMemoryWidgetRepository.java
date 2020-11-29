package com.miro.widget.repository;

import com.miro.widget.domain.Widget;
import com.miro.widget.util.AtomicSequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryWidgetRepository implements Repository<Widget> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryWidgetRepository.class);

    private final AtomicInteger foreground = new AtomicInteger(0);

    private final Semaphore semaphore = new Semaphore(1);

    private final ConcurrentHashMap<Integer, Widget> widgetStorage = new ConcurrentHashMap<>();

    private final Comparator<Widget> compareByZ = Comparator.comparing(Widget::getZ);


    @Override
    public Widget findById(Integer id) {
        return widgetStorage.get(id);
    }

    @Override
    public List<Widget> findAll() {
        List<Widget> widgets = new ArrayList<>(widgetStorage.values());
        widgets.sort(compareByZ);
        return widgets;
    }

    @Override
    public Widget persist(Widget entity) {
        if (entity.getId() == null) {
            entity.setId(AtomicSequenceGenerator.getNext());
        }
        if (entity.getZ() == null) {
            entity.setZ(foreground.incrementAndGet());
        }
        Widget persisted = null;
        try {
            semaphore.acquire();
            persisted = widgetStorage.compute(entity.getId(), (id, widget) -> {
                if (foreground.get() < entity.getZ()) {
                    foreground.set(entity.getZ());
                }
                return entity;
            });
            updateStorage(persisted);
        } catch (InterruptedException exception) {
            LOGGER.error("semaphore exception", exception);
        } finally {
            semaphore.release();
        }

        return persisted;
    }

    private void updateStorage(Widget persisted) {
        widgetStorage.replaceAll((id, widget) -> {
            //increment z for all entries with z >= persisted
            //of course we don't want to update z for our persisted obj
            //TODO handle gaps
            if (!id.equals(persisted.getId()) && widget.getZ().compareTo(persisted.getZ()) >= 0) {
                widget.incrementZindex();
            }
            return widget;
        });
    }

    @Override
    public Widget remove(Integer id) {
        return widgetStorage.remove(id);
    }
}
