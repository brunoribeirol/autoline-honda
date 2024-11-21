package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Customer;
import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.repository.CustomerPhoneRepository;
import com.cesarschool.autoline_honda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPhoneRepository customerPhoneRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerPhoneRepository customerPhoneRepository) {
        this.customerRepository = customerRepository;
        this.customerPhoneRepository = customerPhoneRepository;
    }

    @Transactional
    public void createCustumerWithPhone(Customer customer, CustomerPhone customerPhone) {
        customerRepository.saveCustomer(customer);
        customerPhone.setCustomerCpf(customer.getCpf());
//        customerPhone.setPhoneNumber(customerPhone.getPhoneNumber());
        customerPhoneRepository.saveCustomerPhone(customerPhone);
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
