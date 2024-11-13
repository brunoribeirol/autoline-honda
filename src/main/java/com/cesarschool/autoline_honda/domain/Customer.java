package com.cesarschool.autoline_honda.domain;

import lombok.*;

import java.time.LocalDate;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private String cpf;
    private String name;
    private String driverLicense;
    private String phoneNumber;
    private LocalDate birthDate;
    private String neighborhood;
    private int addressNumber;
    private String state;
    private String zipCode;
    private String street;
    private String city;
}
/*{
    "cpf"
    "name"
    "driverLicense"
    "phoneNumber"
    "birthDate"
    "neighborhood"
    "addressNumber"
    "state"
    "zipCode"
    "street"
    city
}*/