package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    private String addressPK; //address_Pk on the DB
    private String zipCode;
    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
}