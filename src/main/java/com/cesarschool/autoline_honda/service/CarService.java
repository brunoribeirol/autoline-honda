//package com.cesarschool.autoline_honda.service;
//
//import com.cesarschool.autoline_honda.domain.Car;
//import com.cesarschool.autoline_honda.repository.implementation.CarRepositoryImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor // Automatically generates the constructor with required final fields
//public class CarService
//{
//    private final CarRepositoryImpl carRepositoryImpl;
//
//    public List<Car> findAll()
//    {
//        return carRepositoryImpl.findAll();
//    }
//
//    public Car findById(String chassis)
//    {
//        Car car = carRepositoryImpl.findById(chassis);
//        if (car == null) {
//            throw new CarNotFoundException("Car with chassis " + chassis + " not found.");
//        }
//        return car;
//    }
//
//    @Transactional
//    public void save(Car car)
//    {
//        // Validation of the Car object can be done here if necessary
//        carRepositoryImpl.save(car);
//    }
//
//    @Transactional
//    public void update(Car car)
//    {
//        // Checks if the car exists before attempting to update
//        if (carRepositoryImpl.findById(car.getChassis()) == null) {
//            throw new CarNotFoundException("Car with chassis " + car.getChassis() + " not found for update.");
//        }
//        carRepositoryImpl.update(car);
//    }
//
//    @Transactional
//    public void deleteById(String chassis)
//    {
//        // Checks if the car exists before attempting to delete
//        if (carRepositoryImpl.findById(chassis) == null) {
//            throw new CarNotFoundException("Car with chassis " + chassis + " not found for deletion.");
//        }
//        carRepositoryImpl.deleteById(chassis);
//    }
//}
