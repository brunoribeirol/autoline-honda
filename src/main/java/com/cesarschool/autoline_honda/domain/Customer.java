package com.cesarschool.autoline_honda.domain;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private String cpf;
    private String name;
    private String driverLicense;
    private Date birthDate;
    private String neighborhood;
    private int addressNumber;
    private String state;
    private String zipCode;
    private String street;
    private String city;
}