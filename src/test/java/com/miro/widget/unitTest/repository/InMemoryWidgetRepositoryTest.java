package com.miro.widget.unitTest.repository;

import com.miro.widget.domain.Widget;
import com.miro.widget.repository.InMemoryWidgetRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


public class InMemoryWidgetRepositoryTest {
    private final InMemoryWidgetRepository repository = new InMemoryWidgetRepository();

    @Test
    void testCreateWithZ() {
        Widget one = createWidget(100,100, 100, 100, 100);
        Widget persisted = repository.persist(one);
        Widget foundById = repository.findById(persisted.getId());

        assertNotNull(foundById);
        assertEquals(100, (int) foundById.getX());
        assertEquals(100, (int) foundById.getZ());
    }

    @Test
    void testCreateWithoutZ() {
        Widget one = createWidget(100,100, null, 100, 100);
        Widget two = createWidget(200,200, null, 200, 200);
        Widget persistedOne = repository.persist(one);
        Widget persistedTwo = repository.persist(two);

        List<Widget> result = repository.findAll();
        Widget first = result.get(0);
        assertEquals(100, (int) first.getX());
        assertEquals(1, (int) first.getZ());

        Widget second = result.get(1);
        assertEquals(200, (int) second.getX());
        assertEquals(2, (int) second.getZ());
    }

    @Test
    void testCreateAndReturnSortedByZAscending() {
        Widget one = createWidget(100,100, 100, 100, 100);
        Widget two = createWidget(200,200, 100, 200, 200);
        Widget three = createWidget(300,300, 100, 300, 300);
        repository.persist(one);
        repository.persist(two);
        repository.persist(three);

        List<Widget> expected = repository.findAll();
        assertEquals(3, expected.size());

        Widget firstByZ = expected.get(0);
        assertEquals(300, (int) firstByZ.getX());
        assertEquals(100, (int) firstByZ.getZ());

        Widget lastByZ = expected.get(2);
        assertEquals(100, (int) lastByZ.getX());
        assertEquals(102, (int) lastByZ.getZ());

    }

    @Test
    void given1EntryCreated_whenCallRemove_thenRepoIsEmpty() {
        Widget one = createWidget(100,100, 100, 100, 100);
        Widget persisted = repository.persist(one);

        Widget removed = repository.remove(persisted.getId());
        assertSame(persisted.getId(), removed.getId());

        List<Widget> all = repository.findAll();
        assertEquals(0, all.size());
    }

    private Widget createWidget(int x, int y, Integer z, int height, int width) {
        Widget widget = new Widget();
        widget.setX(x);
        widget.setY(y);
        if(z != null) {
            widget.setZ(z);
        }
        widget.setHeight(height);
        widget.setWidth(width);
        return widget;
    }

}