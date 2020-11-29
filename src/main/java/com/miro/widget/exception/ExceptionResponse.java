package com.miro.widget.exception;

import java.time.LocalDateTime;
import java.util.Objects;

public class ExceptionResponse {
    private final LocalDateTime timestamp;
    private final String message;
    private final String requestPath;

    public ExceptionResponse(LocalDateTime timestamp, String message, String requestPath) {
        this.timestamp = timestamp;
        this.message = message;
        this.requestPath = requestPath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionResponse that = (ExceptionResponse) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(message, that.message) &&
                Objects.equals(requestPath, that.requestPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, requestPath);
    }
}
