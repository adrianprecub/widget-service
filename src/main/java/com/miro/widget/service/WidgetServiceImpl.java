package com.miro.widget.service;

import com.miro.widget.controller.WidgetController;
import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.repository.Repository;
import com.miro.widget.transform.WidgetDomainToDtoConverter;
import com.miro.widget.transform.WidgetDtoToDomainConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetServiceImpl implements WidgetService {

    public static final Logger LOGGER = LoggerFactory.getLogger(WidgetController.class);
    private final Repository<Widget> repository;
    private final WidgetDtoToDomainConverter dtoToDomain;
    private final WidgetDomainToDtoConverter domainToDto;

    @Autowired
    public WidgetServiceImpl(Repository<Widget> repository,
                             WidgetDtoToDomainConverter dtoToDomain,
                             WidgetDomainToDtoConverter domainToDto) {
        this.repository = repository;
        this.dtoToDomain = dtoToDomain;
        this.domainToDto = domainToDto;
    }

    @Override
    public WidgetDto create(WidgetDto dto) {
        Widget widget = dtoToDomain.apply(dto);

        Widget persisted = repository.persist(widget);
        return domainToDto.apply(persisted);
    }

    @Override
    public WidgetDto findById(Integer id) {
        Widget found = repository.findById(id);
        if (found != null) {
            return domainToDto.apply(found);
        }

        LOGGER.error("Widget with id [{}] not found!", id);
        throw new WidgetNotFoundException(String.format("Widget with id %s not found!", id));
    }

    @Override
    public List<WidgetDto> findAll() {
        return domainToDto.convert(repository.findAll(), null);
    }

    @Override
    public WidgetDto update(Integer id, WidgetDto dto) {
        Widget toUpdate = repository.findById(id);

        if (toUpdate == null) {
            LOGGER.error("Widget with id [{}] not found!", id);
            throw new WidgetNotFoundException(String.format("Widget with id %s not found!", id));
        }

        Widget newWidget = dtoToDomain.apply(dto, "id");
        newWidget.setId(toUpdate.getId());

        Widget persisted = repository.persist(newWidget);

        return domainToDto.apply(persisted);
    }

    @Override
    public boolean deleteById(Integer id) {
        Widget removed = repository.remove(id);
        if (removed == null) {
            String errMsg = "Widget with id %s not found!";
            throw new WidgetNotFoundException(String.format(errMsg, id));
        }

        return true;
    }
}
