package com.miro.widget.integrationTest;

import com.miro.widget.dto.WidgetDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WidgetControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testCreate_OK() {
        WidgetDto responseDto = createDtoObject();

        assertNotNull(responseDto);
        assertEquals(1, responseDto.getId());
        assertNotNull(responseDto.getLastModificationDate());
    }

    @Test
    @Order(2)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testUpdate_OK() {
        WidgetDto responseDto = createDtoObject();

        assertNotNull(responseDto);
        assertEquals(10, responseDto.getX());
        assertEquals(11, responseDto.getY());
        assertEquals(12, responseDto.getZ());

        responseDto.setX(100);
        responseDto.setY(100);
        responseDto.setZ(100);

        String updateUrl = "http://localhost:" + port + "/api/v1/widgets/" + responseDto.getId();
        ResponseEntity<WidgetDto> newResponseEntity = restTemplate.exchange(updateUrl, HttpMethod.PUT, new HttpEntity<>(responseDto), WidgetDto.class);
        assertEquals(200, newResponseEntity.getStatusCodeValue());
        WidgetDto newResponseDto = newResponseEntity.getBody();

        assertNotNull(newResponseDto);
        assertEquals(2, newResponseDto.getId());
        assertEquals(100, newResponseDto.getX());
        assertEquals(100, newResponseDto.getY());
        assertEquals(100, newResponseDto.getZ());

    }

    @Test
    @Order(3)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindAll_Empty() {
        String finalUrl = "http://localhost:" + port + "/api/v1/widgets";

        ResponseEntity<List> responseEntity = restTemplate.getForEntity(finalUrl, List.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        List responseDto = responseEntity.getBody();
        assertNotNull(responseDto);
        assertEquals(0, responseDto.size());
    }

    @Test
    @Order(4)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void testFindAll_OneElement() {
        String finalUrl = "http://localhost:" + port + "/api/v1/widgets";

        WidgetDto createDto = createDtoObject();
        assertNotNull(createDto);

        ResponseEntity<WidgetDto[]> getResponse = restTemplate.getForEntity(finalUrl, WidgetDto[].class);
        assertEquals(200, getResponse.getStatusCodeValue());
        WidgetDto[] getDtos = getResponse.getBody();
        assertNotNull(getDtos);
        assertEquals(1, getDtos.length);
    }

    private WidgetDto createDtoObject() {
        String createUrl = "http://localhost:" + port + "/api/v1/widgets";
        ResponseEntity<WidgetDto> responseEntity = restTemplate.postForEntity(createUrl, getRequestDtoJson(), WidgetDto.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        return responseEntity.getBody();
    }

    private WidgetDto getRequestDtoJson() {
        WidgetDto toSave = new WidgetDto();
        toSave.setX(10);
        toSave.setY(11);
        toSave.setZ(12);
        toSave.setWidth(13);
        toSave.setHeight(14);
        return toSave;
    }

}
