package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.service.CustomerPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-phones")
public class CustomerPhoneController {

    private final CustomerPhoneService customerPhoneService;

    @Autowired
    public CustomerPhoneController(CustomerPhoneService customerPhoneService) {
        this.customerPhoneService = customerPhoneService;
    }

    @PostMapping
    public ResponseEntity<String> addPhone(@RequestBody CustomerPhone customerPhone) {
        try {
            customerPhoneService.addPhone(customerPhone);
            return new ResponseEntity<>("Phone number added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add phone number", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<CustomerPhone>> getPhonesByCustomerCpf(@PathVariable String cpf) {
        List<CustomerPhone> phones = customerPhoneService.getPhonesByCustomerCpf(cpf);
        if (phones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletePhonesByCustomerCpf(@PathVariable String cpf) {
        try {
            customerPhoneService.deletePhonesByCustomerCpf(cpf);
            return new ResponseEntity<>("Phone numbers deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete phone numbers", HttpStatus.BAD_REQUEST);
        }
    }
}
