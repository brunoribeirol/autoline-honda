package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    private String branchCnpj; //branch_cpnj on the db
    private String zipCode;
    private String street;
    private int addressNumber;
    private String neighborhood;
    private String city;
    private String state;
}