package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Car;
import com.cesarschool.autoline_honda.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity getAllCars() {
        return ResponseEntity.ok("It's working");
    }

    // @PostMapping
}
