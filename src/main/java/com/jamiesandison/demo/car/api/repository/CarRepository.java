package com.jamiesandison.demo.car.api.repository;

import com.jamiesandison.demo.car.api.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByBrandAndModel(String brand, String model);
}
