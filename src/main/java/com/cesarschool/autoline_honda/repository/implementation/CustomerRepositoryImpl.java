package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Customer;
import com.cesarschool.autoline_honda.domain.Goals;
import com.cesarschool.autoline_honda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveCustomer(Customer customer) {
        String sql = "INSERT INTO customer (cpf, name, driver_license, birth_date, neighborhood, address_number, state, zip_code, street, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, customer.getCpf(), customer.getName(), customer.getDriverLicense(),
                customer.getBirthDate(), customer.getNeighborhood(), customer.getAddressNumber(), customer.getState(),
                customer.getZipCode(), customer.getStreet(), customer.getCity());
    }

    @Override
    public int updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name = ?, driver_license = ?, birth_date = ?, neighborhood = ?, address_number = ?, state = ?, zip_code = ?, street = ?, city = ? WHERE cpf = ?";
        return jdbcTemplate.update(sql, customer.getName(), customer.getDriverLicense(),
                customer.getBirthDate(), customer.getNeighborhood(), customer.getAddressNumber(), customer.getState(),
                customer.getZipCode(), customer.getStreet(), customer.getCity(), customer.getCpf());
    }

    @Override
    public int deleteCustomerByCpf(String cpf) {
        String sql = "DELETE FROM customer WHERE cpf = ?";
        return jdbcTemplate.update(sql, cpf);
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        String sql = "SELECT * FROM customer WHERE cpf = ?";
        RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(
                rs.getString("cpf"),
                rs.getString("name"),
                rs.getString("driver_license"),
                rs.getDate("birth_date"),
                rs.getString("neighborhood"),
                rs.getInt("address_number"),
                rs.getString("state"),
                rs.getString("zip_code"),
                rs.getString("street"),
                rs.getString("city")
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, cpf));
    }

    @Override
    public List<Customer> findAllCustomers() {
        String sql = "SELECT * FROM customer";
        RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(
                rs.getString("cpf"),
                rs.getString("name"),
                rs.getString("driver_license"),
                rs.getDate("birth_date"),
                rs.getString("neighborhood"),
                rs.getInt("address_number"),
                rs.getString("state"),
                rs.getString("zip_code"),
                rs.getString("street"),
                rs.getString("city")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
