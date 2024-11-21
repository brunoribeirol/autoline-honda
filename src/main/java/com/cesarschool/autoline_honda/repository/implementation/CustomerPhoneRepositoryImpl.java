package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.repository.CustomerPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerPhoneRepositoryImpl implements CustomerPhoneRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerPhoneRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveCustomerPhone(CustomerPhone customerPhone) {
        String sql = "INSERT INTO CustomerPhone (customer_cpf, phone_number) VALUES (?, ?)";
        return jdbcTemplate.update(sql, customerPhone.getCustomerCpf(), customerPhone.getPhoneNumber());
    }


    @Override
    public List<CustomerPhone> findPhonesByCustomerCpf(String cpf) {
        String sql = "SELECT * FROM CustomerPhone WHERE customer_cpf = ?";
        RowMapper<CustomerPhone> rowMapper = (rs, rowNum) -> new CustomerPhone(
                rs.getString("customer_cpf"),
                rs.getString("phone_number")
        );
        return jdbcTemplate.query(sql, rowMapper, cpf);
    }

    @Override
    public int deletePhonesByCustomerCpf(String cpf) {
        String sql = "DELETE FROM CustomerPhone WHERE customer_cpf = ?";
        return jdbcTemplate.update(sql, cpf);
    }

    public void addPhonesForCustomer(String customerCpf, List<CustomerPhone> phones) {
        phones.forEach(phone -> {
            phone.setCustomerCpf(customerCpf);
            saveCustomerPhone(phone);
        });
    }

}
