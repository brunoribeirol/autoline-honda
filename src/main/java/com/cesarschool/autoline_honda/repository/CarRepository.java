package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Car;
import java.util.List;

public interface CarRepository {
    List<Car> findAll();
    Car findById(String chassis);
    void create(Car car);
    void update(Car car);
    void deleteById(String chassis);
}
