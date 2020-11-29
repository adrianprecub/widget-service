package com.miro.widget.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Widget {

    private Integer id;
    private Integer x;
    private Integer y;
    private Integer z;

    private Integer width;
    private Integer height;

    private LocalDateTime lastModificationDate = LocalDateTime.now();

    public Widget() {
    }

    public void incrementZindex() {
        z++;
    }
}
