package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.exceptions.CarExistsException;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addCar(List<Car> carList) {
        carList.stream().map(this::mapToEntity).forEach(car -> {
            if (carRepository.existsByBrandAndModel(car.getBrand(), car.getModel())) {
                throw new CarExistsException();
            }
            carRepository.saveAll(carList);
        });
    }

    public List<Car> listOfCars() {
        return carRepository.findAll();
    }

    private Car mapToEntity(Car car) {
        return new Car(
                car.getBrand(),
                car.getModel(),
                car.getPrice(),
                car.getYear(),
                car.getMileage(),
                car.getColour());
    }
}
