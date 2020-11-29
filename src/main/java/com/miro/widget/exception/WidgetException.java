package com.miro.widget.exception;

public class WidgetException extends RuntimeException {

    public WidgetException(String msg) {
        super(msg);
    }

    public WidgetException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
