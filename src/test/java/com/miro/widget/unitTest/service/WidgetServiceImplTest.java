package com.miro.widget.unitTest.service;

import com.miro.widget.domain.Widget;
import com.miro.widget.dto.WidgetDto;
import com.miro.widget.exception.WidgetNotFoundException;
import com.miro.widget.repository.InMemoryWidgetRepository;
import com.miro.widget.repository.Repository;
import com.miro.widget.service.WidgetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WidgetServiceImplTest {

    private WidgetServiceImpl service;
    Repository<Widget> repository = new InMemoryWidgetRepository();

    @BeforeEach
    void setUp() {
        service = new WidgetServiceImpl(repository);
    }

    @Test
    void testCreate_OK() {
        WidgetDto toSave = getTestWidgetDto();

        WidgetDto result = service.create(toSave);
        assertNotNull(result);
        assertEquals(10, result.getX());
    }

    @Test
    void testUpdate_OK() {
        WidgetDto toSave = getTestWidgetDto();

        WidgetDto created = service.create(toSave);
        created.setX(15);

        WidgetDto result = service.update(created.getId(), created);

        assertNotNull(result);
        assertEquals(15, result.getX());
    }

    @Test
    void testUpdate_IdNotFound() {
        assertThrows(WidgetNotFoundException.class, () -> {
            service.update(1,new WidgetDto());
        });
    }

    @Test
    void testFindById_OK() {
        WidgetDto dto = getTestWidgetDto();
        WidgetDto saved = service.create(dto);

        WidgetDto result = service.findById(saved.getId());
        assertNotNull(result);
        assertEquals(10, result.getX());
    }

    @Test
    void testFindById_NotFound() {
        assertThrows(WidgetNotFoundException.class, () -> {
            service.findById(1);
        });
    }

    @Test
    void testFindAll() {
        WidgetDto dto = getTestWidgetDto();
        service.create(dto);
        List<WidgetDto> resultList = service.findAll();
        assertNotNull(resultList);
        assertEquals(1, resultList.size());
    }

    @Test
    void testDeleteById_IdExists() {
        WidgetDto dto = getTestWidgetDto();
        WidgetDto saved = service.create(dto);
        boolean deleted = service.deleteById(saved.getId());
        assertTrue(deleted);
    }

    @Test
    void testDeleteById_IdNotExists() {
        assertThrows(WidgetNotFoundException.class, () -> {
            service.deleteById(1);
        });
    }

    private WidgetDto getTestWidgetDto() {
        WidgetDto toSave = new WidgetDto();
        toSave.setX(10);
        toSave.setY(11);
        toSave.setZ(12);
        toSave.setWidth(13);
        toSave.setHeight(14);
        return toSave;
    }

}