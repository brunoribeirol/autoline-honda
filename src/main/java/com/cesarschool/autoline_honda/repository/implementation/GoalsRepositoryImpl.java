package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.Goals;
import com.cesarschool.autoline_honda.repository.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GoalsRepositoryImpl implements GoalsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GoalsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveGoal(Goals goal) {
        String sql = "INSERT INTO goals (goal_date, car_quantity, branch_cnpj) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, goal.getGoalDate(), goal.getCarQuantity(), goal.getBranchCnpj());
    }


    @Override
    public int updateGoal(Goals goal) {
        String sql = "UPDATE goals SET goal_date = ?, car_quantity = ?, branch_cnpj = ? WHERE goal_id = ?";
        return jdbcTemplate.update(sql, goal.getGoalDate(), goal.getCarQuantity(), goal.getBranchCnpj(), goal.getGoalId());
    }

    @Override
    public int deleteGoalByGoalId(int goalId) {
        String sql = "DELETE FROM goals WHERE goal_id = ?";
        return jdbcTemplate.update(sql, goalId);
    }

    @Override
    public Optional<Goals> findGoalByGoalId(int goalId) {
        String sql = "SELECT * FROM goals WHERE goal_id = ?";
        RowMapper<Goals> rowMapper = (rs, rowNum) -> new Goals(
                rs.getInt("goal_id"),
                rs.getDate("goal_date"),
                rs.getInt("car_quantity"),
                rs.getString("branch_cnpj")
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, goalId));
    }

    @Override
    public List<Goals> findAllGoals() {
        String sql = "SELECT * FROM goals";
        RowMapper<Goals> rowMapper = (rs, rowNum) -> new Goals(
                rs.getInt("goal_id"),
                rs.getDate("goal_date"),
                rs.getInt("car_quantity"),
                rs.getString("branch_cnpj")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

}
