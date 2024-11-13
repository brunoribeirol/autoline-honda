package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.TradeInCredit;
import com.cesarschool.autoline_honda.repository.TradeInCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TradeInCreditRepositoryImpl implements TradeInCreditRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TradeInCreditRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveTradeInCredit(TradeInCredit tradeInCredit) {
        String sql = "INSERT INTO tradeincredit (trade_id, customer_cpf, used_car_chassis, value) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, tradeInCredit.getTradeId(), tradeInCredit.getCustomerCpf(),
                tradeInCredit.getUsedCarChassis(), tradeInCredit.getValue());
    }

    @Override
    public int updateTradeInCreditByTradeId(TradeInCredit tradeInCredit) {
        String sql = "UPDATE tradeincredit SET customer_cpf = ?, used_car_chassis = ?, value = ? WHERE trade_id = ?";
        return jdbcTemplate.update(sql, tradeInCredit.getCustomerCpf(), tradeInCredit.getUsedCarChassis(),
                tradeInCredit.getValue(), tradeInCredit.getTradeId());
    }

    @Override
    public int deleteTradeInCreditByTradeId(int tradeId) {
        String sql = "DELETE FROM tradeincredit WHERE trade_id = ?";
        return jdbcTemplate.update(sql, tradeId);
    }

    @Override
    public Optional<TradeInCredit> findTradeInCreditByTradeId(int tradeId) {
        String sql = "SELECT * FROM tradeincredit WHERE trade_id = ?";
        RowMapper<TradeInCredit> rowMapper = (rs, rowNum) -> new TradeInCredit(
                rs.getInt("trade_id"),
                rs.getString("customer_cpf"),
                rs.getString("used_car_chassis"),
                rs.getFloat("value")
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, tradeId));
    }

    @Override
    public List<TradeInCredit> findAllTradeInCredits() {
        String sql = "SELECT * FROM tradeincredit";
        RowMapper<TradeInCredit> rowMapper = (rs, rowNum) -> new TradeInCredit(
                rs.getInt("trade_id"),
                rs.getString("customer_cpf"),
                rs.getString("used_car_chassis"),
                rs.getFloat("value")
        );
        return jdbcTemplate.query(sql, rowMapper);
    }
}
