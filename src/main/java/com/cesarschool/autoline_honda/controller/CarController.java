package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.domain.Specification;
import com.cesarschool.autoline_honda.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create-with-specification")
    public ResponseEntity<String> createCarWithSpecification(@RequestBody Map<String, Object> request)
    {
        Car car = new Car();
        car.setChassis((String) request.get("chassis"));
        car.setPrice((Float) request.get("price"));
        car.setColor((String) request.get("color"));
        car.setWheelSize((Integer) request.get("wheelSize"));
        car.setFuelType((String) request.get("fuelType"));
        car.setYear((Integer) request.get("year"));
        car.setEngine((String) request.get("engine"));
        car.setTransmission((String) request.get("transmission"));
        car.setMileage((Integer) request.get("mileage"));
        car.setCarCondition((String) request.get("carCondition"));

        Specification specification = new Specification();
        specification.setCategory((String) request.get("category"));
        specification.setModel((String) request.get("model"));
        specification.setVersion((String) request.get("version"));

        carService.createCarWithSpecification(car, specification);

        return ResponseEntity.status(HttpStatus.CREATED).body("Car and Specification created successfully");

    }

//    @PostMapping
//    public ResponseEntity<Car> createCar(@RequestBody Car car) {
//        try {
//            Car createdCar = carService.createCar(car);
//            return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/{chassis}")
    public ResponseEntity<Car> getCarByChassis(@PathVariable String chassis) {
        Optional<Car> carOptional = carService.findCarByChassis(chassis);
        return carOptional.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.findAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PutMapping("/{chassis}")
    public ResponseEntity<Car> updateCar(@PathVariable String chassis, @RequestBody Car car) {
        car.setChassis(chassis);
        try {
            Car updatedCar = carService.updateCar(car);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{chassis}")
    public ResponseEntity<Void> deleteCar(@PathVariable String chassis) {
        try {
            carService.deleteCar(chassis);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
