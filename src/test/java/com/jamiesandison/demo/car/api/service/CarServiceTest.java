package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.controller.CarsController;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @Test
    void addingCarTest() {

        carService.addCar(List.of(
                new Car("Dodge",
                        "Challenger",
                        2017,
                        43000,
                        30000,
                        "Gun Metal")));



    }
}
