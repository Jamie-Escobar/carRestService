package com.jamiesandison.demo.car.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarExistsException.class)
    public ResponseEntity<Map<String, String>> handle_Duplicate_Car() {
        return new ResponseEntity<>(Map.of("description", "Car already exists"), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> missing_Attribute_Or_Malformed_Data() {

        return new ResponseEntity<>(Map.of("description", "Incorrect car data provided"), HttpStatus.BAD_REQUEST);
    }

}

//            malformed attribute
//            brand: "BMW", <--- attribute to be a string
//            "model": "X5",
//            "year": 2022,
//            "price": 80000,
//            "mileage": 10000,
//            "colour": "space grey"


