package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")  // Base URL for all car-related endpoints
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Endpoint to create a new car
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            Car createdCar = carService.createCar(car);
            return new ResponseEntity<>(createdCar, HttpStatus.CREATED); // Return status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to get a car by its chassis (primary key)
    @GetMapping("/{chassis}")
    public ResponseEntity<Car> getCarByChassis(@PathVariable String chassis) {
        Optional<Car> carOptional = carService.findCarByChassis(chassis);
        return carOptional.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if not found
    }

    // Endpoint to get all cars
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);  // Return status 200
    }

    // Endpoint to update a car by its chassis
    @PutMapping("/{chassis}")
    public ResponseEntity<Car> updateCar(@PathVariable String chassis, @RequestBody Car car) {
        car.setChassis(chassis);  // Ensure chassis is set in the car object
        try {
            Car updatedCar = carService.updateCar(car);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);  // Return status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to delete a car by its chassis
    @DeleteMapping("/{chassis}")
    public ResponseEntity<Void> deleteCar(@PathVariable String chassis) {
        try {
            carService.deleteCar(chassis);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return status 204 if deletion was successful
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
