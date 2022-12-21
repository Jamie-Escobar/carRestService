package com.jamiesandison.demo.car.api.controller;

import com.jamiesandison.demo.car.api.exceptions.GlobalExceptionHandler;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CarsController {

    @Autowired
    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars/admin")
    public ResponseEntity<Map<String, String>> addCars(@RequestBody List<Car> carList) {
        
        carService.addCar(carList);
        return new ResponseEntity<>(Map.of("description", "Database updated"), HttpStatus.CREATED);
    }

    // @valid to be used in before the @RequestBody
    // need to create logic for the controller above so that any missed or malformed data such as "brand" "colour" etc.
    // returns "description", "Incorrect car data provided"
    // need to create logic for duplicates in the database and return "description", "Car already exists"
    // need to create unit and functional tests for this

    @GetMapping("/cars/admin")
    public ResponseEntity<List<Car>> getListOfCars() {

        return new ResponseEntity<>(carService.listOfCars(), HttpStatus.OK);
    }
}
