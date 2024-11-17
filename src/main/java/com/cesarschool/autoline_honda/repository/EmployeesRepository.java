package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Employees;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository {
    int saveEmployee(Employees employee);
    int updateEmployee(Employees employee);
    int deleteEmployeeByCpf(String cpf);
    Optional<Employees> findEmployeeByCpf(String cpf);
    List<Employees> findAllEmployees();
}
