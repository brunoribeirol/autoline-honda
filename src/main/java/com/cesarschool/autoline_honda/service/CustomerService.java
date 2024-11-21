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

//    @Transactional
//    public void createCustomerWithPhone(Customer customer, CustomerPhone customerPhone) {
//        customerRepository.saveCustomer(customer);
//        customerPhone.setCustomerCpf(customer.getCpf());
////        customerPhone.setPhoneNumber(customerPhone.getPhoneNumber());
//        customerPhoneRepository.saveCustomerPhone(customerPhone);
//    }
//    @Transactional
//    public void createCustomerWithPhone(Customer customer, List<CustomerPhone> phones) {
//        customerRepository.saveCustomer(customer);
//        phones.forEach(phone -> {
//            phone.setCustomerCpf(customer.getCpf());
//            customerPhoneRepository.saveCustomerPhone(phone);
//        });
//    }
    @Transactional
    public void createCustomerWithPhone(Customer customer, List<CustomerPhone> phones) {
        customerRepository.saveCustomer(customer);
        phones.forEach(phone -> {
            phone.setCustomerCpf(customer.getCpf());
            customerPhoneRepository.saveCustomerPhone(phone);
        });
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

    private void savePhonesForCustomer(String cpf, List<CustomerPhone> phones) {
        phones.forEach(phone -> {
            phone.setCustomerCpf(cpf);
            customerPhoneRepository.saveCustomerPhone(phone);
        });
    }

//    public Optional<Customer> getCustomerWithPhones(String cpf) {
//        Optional<Customer> customer = customerRepository.findCustomerByCpf(cpf);
//        customer.ifPresent(c -> c.setPhones(customerPhoneRepository.findPhonesByCustomerCpf(cpf)));
//        return customer;
//    }

    public Optional<Customer> getCustomerByCpf(String cpf) {
        return customerRepository.findCustomerByCpf(cpf);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    public List<Customer> getAllCustomersWithPhones() {
        List<Customer> customers = customerRepository.findAllCustomers();
        customers.forEach(customer ->
                customer.setPhones(customerPhoneRepository.findPhonesByCustomerCpf(customer.getCpf()))
        );
        return customers;
    }

}
