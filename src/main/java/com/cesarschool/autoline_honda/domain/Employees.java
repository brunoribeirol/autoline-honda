package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Employees
{
    private String name;
    private String CPF;
    private String position;
    private String fkSupervisorCPF;
    private String fkBranchCNPJ;
}
