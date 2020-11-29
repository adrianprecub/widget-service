package com.miro.widget.util;

import com.miro.widget.exception.WidgetException;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSequenceGenerator {

    private AtomicSequenceGenerator() {
        throw new WidgetException("y u do dis?");
    }

    private static final AtomicInteger value = new AtomicInteger(1);

    public static int getNext() {
        return value.getAndIncrement();
    }

}
