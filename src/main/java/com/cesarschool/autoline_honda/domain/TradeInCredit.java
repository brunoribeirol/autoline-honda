package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class TradeInCredit {
    private int ID;
    private String fkCustomerCPF;
    private String fkUsedCarChassis;
    private float value;
}
