package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    int saveCustomer(Customer customer);
    int updateCustomer(Customer customer);
    int deleteCustomerByCpf(String cpf);
    Optional<Customer> findCustomerByCpf(String cpf);
    List<Customer> findAllCustomers();
}
