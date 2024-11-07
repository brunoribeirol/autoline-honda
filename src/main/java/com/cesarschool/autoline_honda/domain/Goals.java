package com.cesarschool.autoline_honda.domain;

import java.util.Date;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Goals {
    private int ID;
    private Date date;
    private int car_quantity;
    private String fk_Branch_CNPJ;
}