package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Employees;
import com.cesarschool.autoline_honda.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public Employees createEmployee(Employees employee) {
        employeesRepository.saveEmployee(employee);
        return employee;
    }

    public Employees updateEmployee(Employees employee) {
        employeesRepository.updateEmployee(employee);
        return employee;
    }

    public void deleteEmployeeByCpf(String cpf) {
        int rowsAffected =employeesRepository.deleteEmployeeByCpf(cpf);
        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete employee with CPF: " + cpf);
        }
    }

    public Optional<Employees> findEmployeeByCpf(String cpf) {
        return employeesRepository.findEmployeeByCpf(cpf);
    }

    public List<Employees> findAllEmployees() {
        return employeesRepository.findAllEmployees();
    }
}
