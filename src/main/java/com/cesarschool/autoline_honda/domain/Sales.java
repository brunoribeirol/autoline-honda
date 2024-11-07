package com.cesarschool.autoline_honda.domain;

import lombok.*;

import java.util.Date;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Sales {
    private String fkEmployeesCPF;
    private String fkCarChassis;
    private String fkCustomerCPF;
    private int ID;
    private float finalPrice;
    private String saleStatus;
    private Date date;
    private float discount;
}
