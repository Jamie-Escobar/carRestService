package com.jamiesandison.demo.car.api.Controller;

import com.jamiesandison.demo.car.api.Model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CarsController {

    @PostMapping("cars/admin")
    public ResponseEntity<Map<String, String>> addCars(@RequestBody List<Car> carList) {

        return new ResponseEntity<>(Map.of("description", "Database updated"), HttpStatus.CREATED);
    }
}
