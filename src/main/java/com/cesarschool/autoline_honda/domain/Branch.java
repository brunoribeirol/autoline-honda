package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Branch
{
    private String CNPJ;
    private String name;
    private String fkAddressPK;
}
