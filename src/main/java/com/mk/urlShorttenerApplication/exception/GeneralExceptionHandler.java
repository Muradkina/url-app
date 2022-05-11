package com.mk.urlShorttenerApplication.exception;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.PushBuilder;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(x -> {
                    String fieldName = ((FieldError) x).getField();
                    String errorMessage = x.getDefaultMessage();
                    errors.put(fieldName, errorMessage);

                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<?> shortUrlNotFoundException(ShortUrlNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errors", e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CodeAlreadyExists.class)
    public ResponseEntity<?> CodeAlreadyExists(CodeAlreadyExists e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errors", e.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);
    }
}