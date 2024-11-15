package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.repository.CustomerPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPhoneService {

    private final CustomerPhoneRepository customerPhoneRepository;

    @Autowired
    public CustomerPhoneService(CustomerPhoneRepository customerPhoneRepository) {
        this.customerPhoneRepository = customerPhoneRepository;
    }

    public void addPhone(CustomerPhone customerPhone) {
        customerPhoneRepository.saveCustomerPhone(customerPhone);
    }

    public List<CustomerPhone> getPhonesByCustomerCpf(String cpf) {
        return customerPhoneRepository.findPhonesByCustomerCpf(cpf);
    }

    public void deletePhonesByCustomerCpf(String cpf) {
        customerPhoneRepository.deletePhonesByCustomerCpf(cpf);
    }
}
