package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.domain.Specification;
import com.cesarschool.autoline_honda.repository.CarRepository;
import com.cesarschool.autoline_honda.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final SpecificationRepository specificationRepository;

    @Autowired
    public CarService(CarRepository carRepository, SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public void createCarWithSpecification(Car car, Specification specification) {
        carRepository.saveCar(car);

        specification.setCarChassis(car.getChassis());

        specificationRepository.saveSpecification(specification);
    }


    public Car createCar(Car car) {
        carRepository.saveCar(car);
        // carCondition is automatically determined by the database based on mileage
        return findCarByChassis(car.getChassis()).orElse(car); // Retrieve to include carCondition
    }

    public Car updateCar(Car car) {
        if (carRepository.updateCar(car) > 0) {
            return findCarByChassis(car.getChassis()).orElse(car);
        }
        throw new RuntimeException("Failed to update the car");
    }

    public void deleteCar(String chassis) {
        if (carRepository.deleteCarByChassis(chassis) == 0) {
            throw new RuntimeException("Failed to delete the car with chassis: " + chassis);
        }
    }

    public Optional<Car> findCarByChassis(String chassis) {
        return carRepository.findCarByChassis(chassis);
    }

    public List<Car> findAllCars() {
        return carRepository.findAllCars();
    }

    public List<Car> findAllCarsWithSpecifications() {
        return carRepository.findAllCars();
    }

}
