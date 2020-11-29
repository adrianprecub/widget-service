package com.miro.widget.transform;

import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WidgetConverterTest {

    private final WidgetDtoToDomainConverter converter = new WidgetDtoToDomainConverter();

    @Test
    void testDtoToDomainConvert() {
        WidgetDto dto = new WidgetDto();
        dto.setX(1);
        Widget convert = converter.convert(dto, null);
        assertNotNull(convert);
        assertEquals(1, convert.getX());
    }

    @Test
    void testDtoToDomainConvertList() {
        WidgetDto dto1 = new WidgetDto();
        dto1.setX(1);
        WidgetDto dto2 = new WidgetDto();
        dto2.setX(2);

        List<WidgetDto> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);

        List<Widget> convertList = converter.convert(dtos, null);
        assertNotNull(convertList);
        assertEquals(2, convertList.size());
        assertEquals(1, convertList.get(0).getX());
        assertEquals(2, convertList.get(1).getX());
    }
}