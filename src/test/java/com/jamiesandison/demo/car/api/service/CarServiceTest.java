package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.exceptions.CarExistsException;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    Car exampleTestCar = new Car(
            "BMW",
            "X5",
            80000,
            2022,
            10000,
            "Space Grey");

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

        Mockito.when(carRepository.save(exampleTestCar)).thenThrow(ConstraintViolationException.class);
        List<Car> cars = List.of(exampleTestCar);
        Assertions.assertThrows(ConstraintViolationException.class, () -> carService.addCar(cars));
        Mockito.verify(carRepository, times(1)).save(exampleTestCar);

    }

    @Test
    void throws_Exception_When_Duplicate_In_Database() {

        Mockito.when(carRepository.existsByBrandAndModel(exampleTestCar.getBrand(), exampleTestCar.getModel())).thenReturn(false).thenReturn(true);
        Assertions.assertThrows(CarExistsException.class, () -> carService.addCar(List.of(exampleTestCar, exampleTestCar)));
    }

}
