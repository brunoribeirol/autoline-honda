package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
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
