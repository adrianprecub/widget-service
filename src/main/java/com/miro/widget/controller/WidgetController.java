package com.miro.widget.controller;

import com.miro.widget.dto.WidgetDto;
import com.miro.widget.service.WidgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/widgets")
public class WidgetController {
    public static final Logger LOGGER = LoggerFactory.getLogger(WidgetController.class);

    private final WidgetService widgetService;

    @Autowired
    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @PostMapping
    public ResponseEntity<WidgetDto> createWidget(@Valid @RequestBody WidgetDto widget) {
        LOGGER.debug("Creating widget [{}]", widget.toString());
        return new ResponseEntity<>(widgetService.create(widget), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WidgetDto> updateWidgetById(@NotNull @PathVariable Integer id, @Valid @RequestBody WidgetDto widget) {
        LOGGER.info("Updating widget with id [{}]", id);
        return ResponseEntity.ok(widgetService.update(id, widget));
    }

    @GetMapping
    public ResponseEntity<List<WidgetDto>> findAll() {
        LOGGER.info("Get all widgets!");
        return ResponseEntity.ok(widgetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WidgetDto> findWidgetById(@NotNull @PathVariable Integer id) {
        LOGGER.info("Get widget with id [{}]", id);
        return ResponseEntity.ok(widgetService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteWidgetById(@NotNull @PathVariable Integer id) {
        LOGGER.info("Delete widget with id [{}]", id);
        return new ResponseEntity<>(widgetService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
