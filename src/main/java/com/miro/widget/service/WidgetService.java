package com.miro.widget.service;

import com.miro.widget.dto.WidgetDto;

import java.util.List;

public interface WidgetService {

    WidgetDto create(WidgetDto widget);

    WidgetDto findById(Integer id);

    List<WidgetDto> findAll();

    WidgetDto update(Integer id, WidgetDto updated);

    boolean deleteById(Integer id);
}
