package com.miro.widget.transform;

import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import org.springframework.beans.BeanUtils;

public class WidgetDtoToDomainConverter implements GenericConverter<WidgetDto, String[], Widget> {

    //TODO enhance converter to use existing domain object
    @Override
    public Widget apply(WidgetDto widgetDto, String... propsToIgnore) {
        Widget widget = new Widget();
        BeanUtils.copyProperties(widgetDto, widget, propsToIgnore);

        return widget;
    }
}
