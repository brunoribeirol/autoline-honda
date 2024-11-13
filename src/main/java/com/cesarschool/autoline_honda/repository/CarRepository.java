package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepository {

    /**
     * Save a new car to the database.
     *
     * @param car The car to be saved.
     * @return The number of rows affected.
     */
    int saveCar(Car car);

    /**
     * Update an existing car in the database.
     *
     * @param car The car to be updated.
     * @return The number of rows affected.
     */
    int updateCar(Car car);

    /**
     * Delete a car from the database by its chassis (primary key).
     *
     * @param chassis The chassis of the car to be deleted.
     * @return The number of rows affected.
     */
    int deleteCarByChassis(String chassis);

    /**
     * Find a car in the database by its chassis.
     *
     * @param chassis The chassis of the car to be found.
     * @return An Optional containing the found car, if present.
     */
    Optional<Car> findCarByChassis(String chassis);

    /**
     * Retrieve all cars from the database.
     *
     * @return A list of all cars.
     */
    List<Car> findAllCars();
}
