package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Employees;
import com.cesarschool.autoline_honda.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/employees/{cnpj}")
public class EmployeesController {

    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping("/add")
    public ResponseEntity<Employees> createEmployee(@PathVariable String cnpj, @RequestBody Employees employee) {
        try {
            employee.setBranchCnpj(cnpj);
            Employees createdEmployee = employeesService.createEmployee(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Employees> getEmployeeByCpf(@PathVariable String cpf) {
        Optional<Employees> employeeOptional = employeesService.findEmployeeByCpf(cpf);
        return employeeOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeesService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/{cpf}/edit")
    public ResponseEntity<Employees> updateEmployee(@PathVariable String cpf, @RequestBody Employees employee) {
        employee.setCpf(cpf);
        try {
            //employee.setEmployeeCnpj(cnpj);
            Employees updatedEmployee = employeesService.updateEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String cpf, @PathVariable String cnpj) {
        try {
            employeesService.deleteEmployeeByCpf(cpf);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
