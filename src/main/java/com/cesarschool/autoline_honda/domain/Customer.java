package com.cesarschool.autoline_honda.domain;

import lombok.*;
import java.sql.Date;
import java.util.List;

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

    // Relacionamentos
    private List<CustomerPhone> phones; // Telefones do cliente
    private List<TradeInCredit> tradeInCredits; // Créditos de troca associados ao cliente

    public Customer(String cpf, String name, String driverLicense, Date birthDate, String neighborhood, int addressNumber, String state, String zipCode, String street, String city) {
        this.cpf = cpf;
        this.name = name;
        this.driverLicense = driverLicense;
        this.birthDate = birthDate;
        this.neighborhood = neighborhood;
        this.addressNumber = addressNumber;
        this.state = state;
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
    }

}
