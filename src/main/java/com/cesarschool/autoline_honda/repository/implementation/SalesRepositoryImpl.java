package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Sales;
import com.cesarschool.autoline_honda.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class SalesRepositoryImpl implements SalesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveSale(Sales sale) {
        String sql = "INSERT INTO sales (employee_cpf, car_chassis, customer_cpf, final_price, sale_status, sale_date, discount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sale.getEmployeeCpf(), sale.getCarChassis(), sale.getCustomerCpf(),
                sale.getFinalPrice(), sale.getSaleStatus(), sale.getSaleDate(), sale.getDiscount());
    }

    @Override
    public int updateSale(Sales sale) {
        String sql = "UPDATE sales SET employee_cpf = ?, car_chassis = ?, customer_cpf = ?, final_price = ?, sale_status = ?, sale_date = ?, discount = ? WHERE sale_id = ?";
        return jdbcTemplate.update(sql, sale.getEmployeeCpf(), sale.getCarChassis(), sale.getCustomerCpf(),
                sale.getFinalPrice(), sale.getSaleStatus(), sale.getSaleDate(), sale.getDiscount(), sale.getSaleId());
    }

    @Override
    public int deleteSaleBySaleId(int saleId) {
        String sql = "DELETE FROM sales WHERE sale_id = ?";
        return jdbcTemplate.update(sql, saleId);
    }

    @Override
    public Optional<Sales> findSaleBySaleId(int saleId) {
        String sql = "SELECT * FROM sales WHERE sale_id = ?";
        RowMapper<Sales> rowMapper = new SalesRowMapper();
        List<Sales> salesList = jdbcTemplate.query(sql, rowMapper, saleId);
        return salesList.isEmpty() ? Optional.empty() : Optional.of(salesList.get(0));
    }

    @Override
    public List<Sales> findAllSales() {
        String sql = "SELECT * FROM sales";
        RowMapper<Sales> rowMapper = new SalesRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    private static class SalesRowMapper implements RowMapper<Sales> {
        @Override
        public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Sales(
                    rs.getInt("sale_id"),
                    rs.getString("employee_cpf"),
                    rs.getString("car_chassis"),
                    rs.getString("customer_cpf"),
                    rs.getFloat("final_price"),
                    rs.getString("sale_status"),
                    rs.getDate("sale_date").toLocalDate(),
                    rs.getFloat("discount")
            );
        }
    }
}
