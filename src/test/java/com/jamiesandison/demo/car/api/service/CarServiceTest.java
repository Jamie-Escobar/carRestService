package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.controller.CarsController;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    @Test
    void addingCarTest() {
        List<Car> carList = List.of(
                new Car("Dodge",
                        "Challenger",
                        2017,
                        43000,
                        30000,
                        "Gun Metal"));
        carService.addCar(carList);
        Mockito.verify(carRepository, times(1)).saveAll(carList);
    }
}
