package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.Model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private final CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public void addCar(List<Car> carList) {

        carRepo.saveAll(carList);
    }

}
