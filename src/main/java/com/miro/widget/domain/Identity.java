package com.miro.widget.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@EqualsAndHashCode
@ToString
public abstract class Identity {

    private final String id;

    public Identity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }


}
