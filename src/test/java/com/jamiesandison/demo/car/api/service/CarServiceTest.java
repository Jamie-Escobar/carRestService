package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    void throws_Exception_When_Data_Malformed_Or_Missing() {

        Car car1 = new Car(
                "Audi",
                "R8",
                2020,
                95000,
                5000,
                "Orange");

        Car car2 = new Car(
                "Audi",
                "R8",
                2020,
                95000,
                5000,
                "Orange");

        Mockito.when(carRepository.save(car2)).thenThrow(ConstraintViolationException.class);
        List<Car> carList = List.of(car1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> carService.addCar(carList));

        Mockito.verify(carRepository, times(1)).save(car2);

        // fix this test as it's failing first thing to do on Friday 23rd
    }
}
