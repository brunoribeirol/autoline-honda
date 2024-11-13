package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Employees;
import com.cesarschool.autoline_honda.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")  // Define the base URL for all employee-related endpoints
public class EmployeesController {

    private final EmployeesService employeesService;

    @Autowired
    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    // Endpoint to create a new employee
    @PostMapping
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee) {
        try {
            Employees createdEmployee = employeesService.createEmployee(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);  // Return status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to get an employee by CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Employees> getEmployeeByCpf(@PathVariable String cpf) {
        Optional<Employees> employeeOptional = employeesService.findEmployeeByCpf(cpf);
        return employeeOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if not found
    }

    // Endpoint to get all employees
    @GetMapping
    public ResponseEntity<List<Employees>> getAllEmployees() {
        List<Employees> employees = employeesService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);  // Return status 200
    }

    // Endpoint to update an employee
    @PutMapping("/{cpf}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable String cpf, @RequestBody Employees employee) {
        employee.setCpf(cpf);  // Ensure the CPF is set in the employee object
        try {
            Employees updatedEmployee = employeesService.updateEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);  // Return status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to delete an employee by CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String cpf) {
        try {
            employeesService.deleteEmployeeByCpf(cpf);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return status 204 if deletion was successful
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
