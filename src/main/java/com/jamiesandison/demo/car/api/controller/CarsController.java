package com.jamiesandison.demo.car.api.controller;


import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/cars")
@RestController
@Validated
public class CarsController {

    @Autowired
    private final CarService carService;


    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> addCars(@RequestBody List<@Valid Car> carList) {
        
        carService.addCar(carList);
        return new ResponseEntity<>(Map.of("description", "Database updated"), HttpStatus.CREATED);
    }

    // create Junit test for missing/malformed and duplicates
    // fix and implement cucumber step definitions for missing and malformed fields and attributes

    @GetMapping("/admin")
    public ResponseEntity<List<Car>> getListOfCars() {

        return new ResponseEntity<>(carService.listOfCars(), HttpStatus.OK);
    }
}