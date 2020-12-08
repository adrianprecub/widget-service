package com.miro.widget.transform;

import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class WidgetDomainToDtoConverter implements GenericConverter<Widget, String[], WidgetDto> {

    @Override
    public WidgetDto apply(Widget widget, String... propsToIgnore) {
        WidgetDto dto = new WidgetDto();
        BeanUtils.copyProperties(widget, dto, propsToIgnore);
        return dto;
    }
}
