package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepository {
    // Save a new car
    int saveCar(Car car);

    // Update an existing car
    int updateCar(Car car);

    // Delete a car by its chassis (primary key)
    int deleteCarByChassis(String chassis);

    // Find a car by its chassis
    Optional<Car> findCarByChassis(String chassis);

    // Find all cars
    List<Car> findAllCars();
}
