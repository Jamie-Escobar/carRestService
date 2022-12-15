package com.jamiesandison.demo.car.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarExistsException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCar() {
        return new ResponseEntity<>(Map.of("description", "Car already exists"), HttpStatus.CONFLICT);
    }
}
