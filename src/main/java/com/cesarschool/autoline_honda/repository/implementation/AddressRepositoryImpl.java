package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Address;
import com.cesarschool.autoline_honda.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAddress(Address address) {
        String sql = "INSERT INTO address (branch_cnpj, zip_code, street, address_number, neighborhood, city, state) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, address.getBranchCnpj(), address.getZipCode(), address.getStreet(),
                address.getAddressNumber(), address.getNeighborhood(), address.getCity(), address.getState());
    }

    @Override
    public int updateAddress(Address address) {
        String sql = "UPDATE address SET zip_code = ?, street = ?, address_number = ?, neighborhood = ?, city = ?, state = ? WHERE branch_cnpj = ?";
        return jdbcTemplate.update(sql, address.getZipCode(), address.getStreet(), address.getAddressNumber(),
                address.getNeighborhood(), address.getCity(), address.getState(), address.getBranchCnpj());
    }

    @Override
    public int deleteAddressByBranchCnpj(String branchCnpj) {
        String sql = "DELETE FROM address WHERE branch_cnpj = ?";
        return jdbcTemplate.update(sql, branchCnpj);
    }

    @Override
    public Optional<Address> findAddressByBranchCnpj(String branchCnpj) {
        String sql = "SELECT * FROM address WHERE branch_cnpj = ?";
        RowMapper<Address> rowMapper = (rs, rowNum) -> new Address(
                rs.getString("branch_cnpj"),
                rs.getString("zip_code"),
                rs.getString("street"),
                rs.getInt("address_number"),
                rs.getString("neighborhood"),
                rs.getString("city"),
                rs.getString("state")
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, branchCnpj));
    }

    @Override
    public List<Address> findAllAddresses() {
        String sql = "SELECT * FROM address";
        RowMapper<Address> rowMapper = (rs, rowNum) -> new Address(
                rs.getString("branch_cnpj"),
                rs.getString("zip_code"),
                rs.getString("street"),
                rs.getInt("address_number"),
                rs.getString("neighborhood"),
                rs.getString("city"),
                rs.getString("state")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
