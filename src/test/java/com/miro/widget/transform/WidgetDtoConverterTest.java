package com.miro.widget.transform;

import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WidgetDtoConverterTest {

    private final WidgetDomainToDtoConverter dtoConverter = new WidgetDomainToDtoConverter();

    @Test
    void testDomainToDtoConvert() {
        Widget domain = new Widget();
        domain.setX(1);

        WidgetDto convert = dtoConverter.convert(domain, null);
        assertNotNull(convert);
        assertEquals(1, convert.getX());
        assertNotNull(convert.getLastModificationDate());
    }

    @Test
    void testDomainToDtoConvertList() {
        Widget domain1 = new Widget();
        domain1.setX(1);
        Widget domain2 = new Widget();
        domain2.setX(2);

        List<Widget> widgets = new ArrayList<>();
        widgets.add(domain1);
        widgets.add(domain2);
        List<WidgetDto> convertList = dtoConverter.convert(widgets, null);
        assertNotNull(convertList);
        assertEquals(2, convertList.size());
        assertEquals(1, convertList.get(0).getX());
        assertEquals(2, convertList.get(1).getX());
    }
}