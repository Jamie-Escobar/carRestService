package com.jamiesandison.demo.car.api.exceptions;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.InvalidPropertiesFormatException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    ResponseEntity<Map<String, String>> duplicateEntityException() {
        return new ResponseEntity<>(Map.of("description", "Car already exists"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CarExistsException.class)
    public ResponseEntity<Map<String, String>> handle_Duplicate_Car() {
        return new ResponseEntity<>(Map.of("description", "Car already exists"),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handle_Missing_Attribute_Or_Malformed_Data() {
        return new ResponseEntity<>(Map.of("description", "Incorrect car data provided"),
                HttpStatus.BAD_REQUEST);
    }

    // this exceptionHandler below may need refactoring in the parenthesis =
    // InvalidPropertiesFormatException.class
    @ExceptionHandler(InvalidPropertiesFormatException.class)
    public ResponseEntity<Map<String, String>> handle_Missing_Data_In_Query() {
        return new ResponseEntity<>(Map.of("description", "Incorrect query parameter provided"),
                HttpStatus.BAD_REQUEST);
    }
}


