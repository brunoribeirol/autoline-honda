package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Customer;
import com.cesarschool.autoline_honda.domain.CustomerPhone;
import com.cesarschool.autoline_honda.domain.Goals;
import com.cesarschool.autoline_honda.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        String sql = "INSERT INTO customer (cpf, name, driver_license, birth_date, neighborhood, address_number, state, zip_code, street, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        try {
            Customer customer = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Customer(
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
            ), cpf);
            return Optional.ofNullable(customer);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        String sql = """
            SELECT c.cpf, c.name, c.driver_license, c.birth_date, c.neighborhood, c.address_number, c.state, c.zip_code, c.street, c.city, cp.phone_number
            FROM Customer c
            LEFT JOIN CustomerPhone cp ON cp.customer_cpf = c.cpf
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // Cria o cliente
            Customer customer = new Customer(
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

            // Mapeia o telefone (se existir)
            String phoneNumber = rs.getString("phone_number");
            if (phoneNumber != null) {
                customer.setPhones(List.of(new CustomerPhone(rs.getString("cpf"), phoneNumber)));
            }

            return customer;
        });
    }


}
//    @Override
//    public Optional<Customer> findCustomerByCpf(String cpf) {
//        String sql = """
//        SELECT c.cpf, c.name, c.driver_license, c.birth_date, c.neighborhood, c.address_number, c.state, c.zip_code, c.street, c.city,
//               cp.phone_number,
//               tc.trade_id, tc.used_car_chassis, tc.value
//        FROM Customer c
//        LEFT JOIN CustomerPhone cp ON c.cpf = cp.customer_cpf
//        LEFT JOIN TradeInCredit tc ON c.cpf = tc.customer_cpf
//        WHERE c.cpf = ?
//    """;
//
//        try {
//            return Optional.of(jdbcTemplate.query(sql, rs -> {
//                Customer customer = null;
//
//                while (rs.next()) {
//                    if (customer == null) {
//                        customer = new Customer(
//                                rs.getString("cpf"),
//                                rs.getString("name"),
//                                rs.getString("driver_license"),
//                                rs.getDate("birth_date"),
//                                rs.getString("neighborhood"),
//                                rs.getInt("address_number"),
//                                rs.getString("state"),
//                                rs.getString("zip_code"),
//                                rs.getString("street"),
//                                rs.getString("city")
//                        );
//                        customer.setPhones(new ArrayList<>());
//                        customer.setTradeInCredits(new ArrayList<>());
//                    }
//
//                    // Adiciona os telefones ao cliente
//                    String phoneNumber = rs.getString("phone_number");
//                    if (phoneNumber != null) {
//                        customer.getPhones().add(new CustomerPhone(customer.getCpf(), phoneNumber));
//                    }
//
//                    // Adiciona os créditos de troca ao cliente
//                    int tradeId = rs.getInt("trade_id");
//                    if (!rs.wasNull()) {
//                        customer.getTradeInCredits().add(new TradeInCredit(
//                                tradeId,
//                                customer.getCpf(),
//                                rs.getString("used_car_chassis"),
//                                rs.getFloat("value")
//                        ));
//                    }
//                }
//
//                return customer;
//            }, cpf));
//        } catch (Exception e) {
//            return Optional.empty();
//        }
//    }

//    @Override
//    public Optional<Customer> findCustomerByCpf(String cpf) {
//        String sql = "SELECT * FROM customer WHERE cpf = ?";
//        RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(
//                rs.getString("cpf"),
//                rs.getString("name"),
//                rs.getString("driver_license"),
//                rs.getDate("birth_date"),
//                rs.getString("neighborhood"),
//                rs.getInt("address_number"),
//                rs.getString("state"),
//                rs.getString("zip_code"),
//                rs.getString("street"),
//                rs.getString("city")
//        );
//        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, cpf));
//    }
//    @Override
//    public List<Customer> findAllCustomers() {
//        String sql = """
//        SELECT  c.cpf, c.name, c.driver_license, c.birth_date, c.neighborhood, c.address_number, c.state, c.zip_code, c.street, c.city, cp.phone_number
//        FROM Customer c
//        LEFT JOIN CustomerPhone cp ON cp.customer_cpf = c.cpf
//    """;
//
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Customer customer = new Customer(
//                    rs.getString("cpf"),
//                    rs.getString("name"),
//                    rs.getString("driver_license"),
//                    rs.getDate("birth_date"),
//                    rs.getString("neighborhood"),
//                    rs.getInt("address_number"),
//                    rs.getString("state"),
//                    rs.getString("zip_code"),
//                    rs.getString("street"),
//                    rs.getString("city")
//            );
//
//            CustomerPhone customerPhone = new CustomerPhone(
//                    rs.getString("phone_number"),
//                    customer.getCpf()
//            );
//
//            customer.setCustomerPhone(customerPhone);
//
//            return customer;
//
//        });
//    }
