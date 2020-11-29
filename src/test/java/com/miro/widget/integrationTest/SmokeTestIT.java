package com.miro.widget.integrationTest;

import com.miro.widget.controller.WidgetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class SmokeTestIT {
    @Autowired
    private WidgetController controller;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

}
