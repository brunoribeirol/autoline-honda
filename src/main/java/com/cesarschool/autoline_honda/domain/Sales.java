package com.cesarschool.autoline_honda.domain;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;


@Data // Automatically generates getters, setters, toString, equals e hashCode
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
