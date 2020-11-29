package com.miro.widget.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleGenericExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({WidgetException.class})
    public final ResponseEntity<Object> handleGenericWidgetExceptions(WidgetException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                ex.getMessage(), request.getContextPath());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({WidgetNotFoundException.class})
    public final ResponseEntity<ExceptionResponse> widgetNotFoundException(WidgetNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                ex.getMessage(), request.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(),
                errors.toString(), request.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    //TODO add more cases besides the 404 and generic

}
