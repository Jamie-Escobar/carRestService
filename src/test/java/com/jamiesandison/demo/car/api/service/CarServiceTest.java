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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepository carRepository;

    private final List<Car> carList = new ArrayList<>();
    private Car exampleTestCar = new Car(
                "BMW",
                "X5",
                80000,
                2022,
                10000,
                "Space Grey");

    @Test
    void addingCarTest() {

        List<Car> carList = List.of(exampleTestCar);
        carService.addCar(carList);
        Mockito.verify(carRepository, times(1)).saveAll(carList);
    }

    @Test
    void throws_Exception_When_Data_Malformed_Or_Missing() {

        List<Car> cars = new ArrayList<>();
        cars.add(exampleTestCar = new Car(
                "BMW",
                "X5",
                2001,
                20000,
                10000,
                "Space Grey"));

        when(carRepository.saveAll(cars)).thenThrow(ConstraintViolationException.class);
        ConstraintViolationException constraintViolationException = Assertions.assertThrows(ConstraintViolationException.class, () -> carRepository.saveAll(cars));
        Mockito.verify(carRepository, times(1)).saveAll(cars);
    }

    @Test
    void throws_Exception_When_Duplicate_In_Database() {

        when(carRepository.existsByBrandAndModel(exampleTestCar.getBrand(), exampleTestCar.getModel())).thenReturn(false).thenReturn(true);
        Assertions.assertThrows(CarExistsException.class, () -> carService.addCar(List.of(exampleTestCar, exampleTestCar)));
    }

    @Test
    void gettingAllCars() {

        when(carService.getAllCars()).thenReturn(carList);
        List<Car> allCars = carService.getAllCars();
        Assertions.assertEquals(carList, allCars);
    }

    @Test
    void gettingCarByBrand() {

        when(carRepository.findByBrand(anyString())).thenReturn(carList);
        List<Car> carByName = carService.getCarListByBrand("BMW");
        Assertions.assertEquals(carByName, carList);
    }

    @Test
    void gettingCarByModel() {

        when(carRepository.findByModel(anyString())).thenReturn(carList);
        List<Car> carByModel = carService.getCarListByModel("X5");
        Assertions.assertEquals(carByModel, carList);
    }

    @Test
    void gettingCarByYear() {

        when(carRepository.findByYear(anyInt())).thenReturn(carList);
        List<Car> carByYear = carService.getCarListByYear(2001);
        Assertions.assertEquals(carByYear, carList);
    }

    @Test
    void gettingCarByPrice() {

        when(carRepository.findByPrice(anyInt())).thenReturn(carList);
        List<Car> carByPrice = carService.getCarListByPrice(20000);
        Assertions.assertEquals(carByPrice, carList);
    }

    @Test
    void gettingCarByMileage() {

        when(carRepository.findByMileage(anyInt())).thenReturn(carList);
        List<Car> carByMileage = carService.getCarListByMileage(10000);
        Assertions.assertEquals(carByMileage, carList);
    }

    @Test
    void gettingCarByColour() {

        when(carRepository.findByColour(anyString())).thenReturn(carList);
        List<Car> carByColour = carService.getCarListByColour("Space Grey");
        Assertions.assertEquals(carByColour, carList);
    }
}
