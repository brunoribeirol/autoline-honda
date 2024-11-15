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
}