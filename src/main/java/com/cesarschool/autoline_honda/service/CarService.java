package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Create a new car
    public Car createCar(Car car) {
        carRepository.saveCar(car);
        return car;
    }

    // Update an existing car
    public Car updateCar(Car car) {
        if (carRepository.updateCar(car) > 0) {
            return car;
        }
        throw new RuntimeException("Failed to update the car");
    }

    // Delete a car by chassis
    public void deleteCar(String chassis) {
        if (carRepository.deleteCarByChassis(chassis) == 0) {
            throw new RuntimeException("Failed to delete the car with chassis: " + chassis);
        }
    }

    // Find a car by chassis
    public Optional<Car> findCarByChassis(String chassis) {
        return carRepository.findCarByChassis(chassis);
    }

    // Find all cars
    public List<Car> findAllCars() {
        return carRepository.findAllCars();
    }
}
