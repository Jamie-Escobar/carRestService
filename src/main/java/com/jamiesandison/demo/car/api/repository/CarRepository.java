package com.jamiesandison.demo.car.api.repository;

import com.jamiesandison.demo.car.api.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByBrandAndModel(String brand, String model);
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
    List<Car> findByYear(int year);
    List<Car> findByPrice(int price);
    List<Car> findByMileage(int mileage);
    List<Car> findByColour(String colour);
}
