package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Employees;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository {
    // Save a new employee
    int saveEmployee(Employees employee);

    // Update an existing employee
    int updateEmployee(Employees employee);

    // Delete an employee by CPF
    int deleteEmployeeByCpf(String cpf);

    // Find an employee by CPF
    Optional<Employees> findEmployeeByCpf(String cpf);

    // Find all employees
    List<Employees> findAllEmployees();
}
