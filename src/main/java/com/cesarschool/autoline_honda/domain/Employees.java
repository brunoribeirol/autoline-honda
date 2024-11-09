package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Employees {
    private String name;
    private String cpf;
    private float salary;
    private String position;
    private String supervisorCpf;
    private String branchCnpj;
}
