package com.cesarschool.autoline_honda.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sales {
    private int saleId;
    private String employeeCpf;
    private String carChassis;
    private String customerCpf;
    private float finalPrice;
    private String saleStatus;
    private LocalDate saleDate;
    private float discount;
}
