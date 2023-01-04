package com.jamiesandison.demo.car.api.controller;

import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class CarsControllerTest {

    @InjectMocks
    private CarsController controller; // naming convention instead of controller (sut) = system under test
    @Mock
    private CarService carService;

    @Test
    void addCar_Returns_Response_201() {

        ResponseEntity<Map<String, String>> response = controller.addCar(List.of(
                new Car(
                        "Ford",
                        "mustang",
                        2022,
                        40000,
                        10,
                        "magenta")));

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey("description"));
        Assertions.assertEquals("Database updated", response.getBody().get("description"));

        Mockito.verify(carService, Mockito.times(1)).addCar(Mockito.anyList());
    }

    @Test
    void getting_List_Of_Cars_Returns_Response_200() {

        ResponseEntity<List<Car>> carListAndResponse = controller.getListOfCars();
        Assertions.assertEquals(HttpStatus.OK, carListAndResponse.getStatusCode());
    }

}
