package com.cesarschool.autoline_honda.domain;

import java.util.Date;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Goals {
    private int id;
    private Date goalDate;
    private int carQuantity;
    private String branchCnpj;
}