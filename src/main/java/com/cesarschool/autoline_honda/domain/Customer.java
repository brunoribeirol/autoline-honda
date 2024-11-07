package com.cesarschool.autoline_honda.domain;

import lombok.*;

import java.util.Date;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private String CPF;
    private String name;
    private String driverLicense;
    private String phoneNumber;
    private Date birth_date;
    private String neighborhood;
    private int number;
    private String state;
    private String zipCode;
    private String street;
    private String city;
}
