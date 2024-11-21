package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Customer;
import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> createCustomerWithPhone(
//            @RequestBody Map<String, Object> request) {
//
//            Customer customer = new Customer();
//            customer.setCpf((String) request.get("cpf"));
//            customer.setName((String) request.get("name"));
//            customer.setDriverLicense((String) request.get("driverLicense"));
//            customer.setBirthDate((Date) request.get("birthDate"));
//            customer.setNeighborhood((String) request.get("neighborhood"));
//            customer.setAddressNumber((Integer) request.get("addressNumber"));
//            customer.setState((String) request.get("state"));
//            customer.setZipCode((String) request.get("zipCode"));
//            customer.setStreet((String) request.get("street"));
//            customer.setCity((String) request.get("city"));
//
//            CustomerPhone customerPhone = new CustomerPhone();
//            customerPhone.setPhoneNumber((String) request.get("phoneNumber"));
//
//            customerService.createCustumerWithPhone(customer, customerPhone);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Customer and CustomerPhone created successfully");
//    }
//    @PostMapping("/add")
//    public ResponseEntity<String> createCustomerWithPhones(@RequestBody Map<String, Object> request) {
//        try {
//            Customer customer = new Customer();
//            customer.setCpf((String) request.get("cpf"));
//            customer.setName((String) request.get("name"));
//            customer.setDriverLicense((String) request.get("driverLicense"));
//            customer.setBirthDate(Date.valueOf((String) request.get("birthDate"))); // Converter para Date
//            customer.setNeighborhood((String) request.get("neighborhood"));
//            customer.setAddressNumber((Integer) request.get("addressNumber"));
//            customer.setState((String) request.get("state"));
//            customer.setZipCode((String) request.get("zipCode"));
//            customer.setStreet((String) request.get("street"));
//            customer.setCity((String) request.get("city"));
//
//            // Processar lista de telefones
//            List<CustomerPhone> phones = ((List<Map<String, String>>) request.get("phones")).stream()
//                    .map(phoneData -> new CustomerPhone(customer.getCpf(), phoneData.get("phoneNumber")))
//                    .toList();
//
//            customerService.createCustomerWithPhone(customer, phones);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Customer and phones created successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating customer: " + e.getMessage());
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<String> createCustomerWithPhones(@RequestBody Map<String, Object> request) {
        try {
            Customer customer = new Customer();
            customer.setCpf((String) request.get("cpf"));
            customer.setName((String) request.get("name"));
            customer.setDriverLicense((String) request.get("driverLicense"));
            customer.setBirthDate(Date.valueOf((String) request.get("birthDate"))); // Converte string para Date
            customer.setNeighborhood((String) request.get("neighborhood"));
            customer.setAddressNumber(Integer.parseInt(request.get("addressNumber").toString())); // Corrige Inteiros
            customer.setState((String) request.get("state"));
            customer.setZipCode((String) request.get("zipCode"));
            customer.setStreet((String) request.get("street"));
            customer.setCity((String) request.get("city"));

            // Converte telefones em uma lista de objetos
            List<CustomerPhone> phones = ((List<Map<String, String>>) request.get("phones"))
                    .stream()
                    .map(phoneData -> new CustomerPhone(customer.getCpf(), phoneData.get("phoneNumber")))
                    .toList();

            customerService.createCustomerWithPhone(customer, phones);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer and phones created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating customer: " + e.getMessage());
        }
    }

    @PutMapping("/{cpf}")
        public ResponseEntity<Customer> updateCustomer(@PathVariable String cpf, @RequestBody Customer customer) {
            try {
                customer.setCpf(cpf);
                Customer updatedCustomer = customerService.updateCustomer(customer);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

        @DeleteMapping("/{cpf}")
        public ResponseEntity<Void> deleteCustomer(@PathVariable String cpf) {
            try {
                customerService.deleteCustomerByCpf(cpf);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/{cpf}")
        public ResponseEntity<Customer> getCustomerByCpf(@PathVariable String cpf) {
            Optional<Customer> customer = customerService.getCustomerByCpf(cpf);
            return customer.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping
        public ResponseEntity<List<Customer>> getAllCustomers() {
            List<Customer> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
}
