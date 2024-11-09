package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Branch;
import com.cesarschool.autoline_honda.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Branch> rowMapper = new BranchRowMapper();

    @Autowired
    public BranchRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveBranch(Branch branch) {
        String sql = "INSERT INTO Branch (cnpj, name) VALUES (?, ?)";
        return jdbcTemplate.update(sql, branch.getCnpj(), branch.getName());
    }

    @Override
    public int updateBranch(Branch branch) {
        String sql = "UPDATE Branch SET name = ? WHERE cnpj = ?";
        return jdbcTemplate.update(sql, branch.getName(), branch.getCnpj());
    }

    @Override
    public int deleteBranchByCnpj(String cnpj) {
        String sql = "DELETE FROM Branch WHERE cnpj = ?";
        return jdbcTemplate.update(sql, cnpj);
    }

    @Override
    public Optional<Branch> findBranchByCnpj(String cnpj) {
        String sql = "SELECT * FROM Branch WHERE cnpj = ?";
        try {
            Branch branch = jdbcTemplate.queryForObject(sql, rowMapper, cnpj);
            return Optional.ofNullable(branch);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Branch> findAllBranches() {
        String sql = "SELECT * FROM Branch";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Implementação do RowMapper para Branch
    private static class BranchRowMapper implements RowMapper<Branch> {
        @Override
        public Branch mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Branch(
                    rs.getString("cnpj"),
                    rs.getString("name")
            );
        }
    }
}
