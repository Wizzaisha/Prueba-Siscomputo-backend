package com.example.spring_proxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException exception, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", new Date());
//        body.put("path", request.getDescription(false));

        body.put("error", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceUnavailableException.class})
    public ResponseEntity<?> handleResourceUnavailableException (ResourceUnavailableException exception, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_GATEWAY);
    }


}
