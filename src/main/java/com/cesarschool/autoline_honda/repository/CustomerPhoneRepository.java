package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.CustomerPhone;

import java.util.List;

public interface CustomerPhoneRepository {
    int saveCustomerPhone(CustomerPhone customerPhone);
    List<CustomerPhone> findPhonesByCustomerCpf(String cpf);
    int deletePhonesByCustomerCpf(String cpf);
}
