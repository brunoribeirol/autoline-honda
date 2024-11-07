package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Car
{
    private String chassis;
    private float price;
    private String color;
    private int wheels;
    private String fuelType;
    private String fkSpecificationPK;
    private int year;
    private String engine;
    private String transmission;
}
