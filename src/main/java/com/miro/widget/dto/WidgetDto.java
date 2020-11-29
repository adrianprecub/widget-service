package com.miro.widget.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class WidgetDto {
    private Integer id;
    @NotNull
    private Integer x;
    @NotNull
    private Integer y;
    //can be null
    private Integer z;
    @Positive(message = "Width must be a positive number!")
    @NotNull
    private Integer width;
    @Positive(message = "Width must be a positive number!")
    @NotNull
    private Integer height;
    private LocalDateTime lastModificationDate;

}
