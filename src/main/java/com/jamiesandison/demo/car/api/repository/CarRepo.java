package com.jamiesandison.demo.car.api.repository;

import com.jamiesandison.demo.car.api.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {

}
