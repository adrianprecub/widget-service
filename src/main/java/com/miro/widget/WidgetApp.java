package com.miro.widget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.miro.widget"})
public class WidgetApp {

    public static void main(String[] args) {
        SpringApplication.run(WidgetApp.class, args);
    }
}
