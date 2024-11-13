package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Customer;
import com.cesarschool.autoline_honda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
        return customer;
    }

    public void deleteCustomerByCpf(String cpf) {
        customerRepository.deleteCustomerByCpf(cpf);
    }

    public Optional<Customer> getCustomerByCpf(String cpf) {
        return customerRepository.findCustomerByCpf(cpf);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }
}
