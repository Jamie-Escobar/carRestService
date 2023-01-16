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
    public ResponseEntity<Map<String, String>> addCar(@RequestBody List<@Valid Car> carList) {

        carService.addCar(carList);
        return new ResponseEntity<>(Map.of("description", "Database updated"), HttpStatus.CREATED);
    }


    @GetMapping("/admin")
    public ResponseEntity<List<Car>> getCar(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "price", required = false) Integer price,
            @RequestParam(value = "mileage", required = false) Integer mileage,
            @RequestParam(value = "colour", required = false) String colour) {

        List<Car> cars;

        if (brand != null) {
            cars = carService.getCarListByBrand(brand);
        } else if (model != null) {
            cars = carService.getCarListByModel(model);
        } else if (year != null) {
            cars = carService.getCarListByYear(year);
        } else if (price != null) {
            cars = carService.getCarListByPrice(price);
        } else if (mileage != null) {
            cars = carService.getCarListByMileage(mileage);
        } else if (colour != null) {
            cars = carService.getCarListByColour(colour);
        } else {
            cars = carService.getAllCars();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // need to create an exception handler for = "description": "Incorrect query parameter provided" (done)
    // to return a 400 status code with the message (done)
    // to return a message for missing data in the url (not null or whitespaces)
    // query param to contains letters when it should be an Integer error
    // sort out task for gradle build to get jar file
}