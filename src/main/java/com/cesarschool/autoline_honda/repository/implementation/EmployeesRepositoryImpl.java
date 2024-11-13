package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Employees;
import com.cesarschool.autoline_honda.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeesRepositoryImpl implements EmployeesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveEmployee(Employees employee) {
        String sql = "INSERT INTO employees (name, cpf, salary, position, supervisor_cpf, branch_cnpj) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, employee.getName(), employee.getCpf(), employee.getSalary(),
                employee.getPosition(), employee.getSupervisorCpf(), employee.getBranchCnpj());
    }

    @Override
    public int updateEmployee(Employees employee) {
        String sql = "UPDATE employees SET name = ?, salary = ?, position = ?, supervisor_cpf = ?, branch_cnpj = ? WHERE cpf = ?";
        return jdbcTemplate.update(sql, employee.getName(), employee.getSalary(), employee.getPosition(),
                employee.getSupervisorCpf(), employee.getBranchCnpj(), employee.getCpf());
    }

    @Override
    public int deleteEmployeeByCpf(String cpf) {
        String sql = "DELETE FROM employees WHERE cpf = ?";
        return jdbcTemplate.update(sql, cpf);
    }

    @Override
    public Optional<Employees> findEmployeeByCpf(String cpf) {
        String sql = "SELECT * FROM employees WHERE cpf = ?";
        RowMapper<Employees> rowMapper = (rs, rowNum) -> new Employees(
                rs.getString("name"),
                rs.getString("cpf"),
                rs.getFloat("salary"),
                rs.getString("position"),
                rs.getString("supervisor_cpf"),
                rs.getString("branch_cnpj")
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, cpf));
    }

    @Override
    public List<Employees> findAllEmployees() {
        String sql = "SELECT * FROM employees";
        RowMapper<Employees> rowMapper = (rs, rowNum) -> new Employees(
                rs.getString("name"),
                rs.getString("cpf"),
                rs.getFloat("salary"),
                rs.getString("position"),
                rs.getString("supervisor_cpf"),
                rs.getString("branch_cnpj")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
