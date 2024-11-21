package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String chassis;
    private float price;
    private String color;
    private int wheelSize;
    private String fuelType;
    private int year;
    private String engine;
    private String transmission;
    private int mileage;
    private String carCondition;
    private Specification specification; // Relacionamento com Specification

    public Car(String chassis, float price, String color, int wheelSize, String fuelType, int year, String engine, String transmission, int mileage, String carCondition) {
        this.chassis = chassis;
        this.price = price;
        this.color = color;
        this.wheelSize = wheelSize;
        this.fuelType = fuelType;
        this.year = year;
        this.engine = engine;
        this.transmission = transmission;
        this.mileage = mileage;
        this.carCondition = carCondition;
    }
}
