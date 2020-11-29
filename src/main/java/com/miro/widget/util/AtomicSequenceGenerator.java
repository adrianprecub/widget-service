package com.miro.widget.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSequenceGenerator {
    private static final AtomicInteger value = new AtomicInteger(1);

    public static int getNext() {
        return value.getAndIncrement();
    }

}
