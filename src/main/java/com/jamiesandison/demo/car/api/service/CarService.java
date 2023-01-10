package com.jamiesandison.demo.car.api.service;

import com.jamiesandison.demo.car.api.exceptions.CarExistsException;
import com.jamiesandison.demo.car.api.model.Car;
import com.jamiesandison.demo.car.api.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private static final Sort sortedCarsByBrand = Sort.by(Sort.Direction.ASC, "brand");

    public void addCar(List<Car> carList) {

        carList.stream().map(this::mapToEntity).forEach(car -> {
            if (carRepository.existsByBrandAndModel(car.getBrand(), car.getModel())) {
                throw new CarExistsException();
            }
            carRepository.saveAll(carList);
        });
    }

    public List<Car> getAllCars() {
        return carRepository.findAll(sortedCarsByBrand);
    }

    public List<Car> getCarListByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }

    public List<Car> getCarListByModel(String model) {
        return carRepository.findByModel(model);
    }
    public List<Car> getCarListByYear(int year) {
        return carRepository.findByYear(year);
    }

    public List<Car> getCarListByPrice(int price) {
        return carRepository.findByPrice(price);
    }

    public List<Car> getCarListByMileage(int mileage) {
        return carRepository.findByMileage(mileage);
    }

    public List<Car> getCarListByColour(String colour) {
        return carRepository.findByColour(colour);
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
