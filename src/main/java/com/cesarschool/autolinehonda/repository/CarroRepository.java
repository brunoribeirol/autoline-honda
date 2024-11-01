package com.cesarschool.autolinehonda.repository;

import com.cesarschool.autolinehonda.model.Carro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarroRepository
{
    private final JdbcTemplate jdbcTemplate;

    public CarroRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Carro> carroRowMapper = (rs, rowNum) -> new Carro
            (
            rs.getString("chassi"),
            rs.getInt("preco"),
            rs.getString("cor"),
            rs.getInt("roda"),
            rs.getString("tipo_combustivel"),
            rs.getString("fk_especificacao_PK"),
            rs.getInt("ano"),
            rs.getString("motor"),
            rs.getString("cambio")
    );

    public List<Carro> findAll()
    {
        String sql = "SELECT * FROM carro";
        return jdbcTemplate.query(sql, carroRowMapper);
    }

    public Carro findById(String chassi)
    {
        String sql = "SELECT * FROM carro WHERE chassi = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{chassi}, carroRowMapper);
    }

    public int save(Carro carro)
    {
        String sql = "INSERT INTO carro (chassi, preco, cor, roda, tipo_combustivel, fk_especificacao_PK, ano, motor, cambio) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                carro.getChassi(), carro.getPreco(), carro.getCor(), carro.getRoda(),
                carro.getTipoCombustivel(), carro.getFkEspecificacaoPk(), carro.getAno(),
                carro.getMotor(), carro.getCambio()
        );
    }

    public int update(Carro carro)
    {
        String sql = "UPDATE carro SET preco = ?, cor = ?, roda = ?, tipo_combustivel = ?, " +
                "fk_especificacao_PK = ?, ano = ?, motor = ?, cambio = ? WHERE chassi = ?";
        return jdbcTemplate.update(sql,
                carro.getPreco(), carro.getCor(), carro.getRoda(),
                carro.getTipoCombustivel(), carro.getFkEspecificacaoPk(),
                carro.getAno(), carro.getMotor(), carro.getCambio(),
                carro.getChassi()
        );
    }

    public int deleteById(String chassi)
    {
        String sql = "DELETE FROM carro WHERE chassi = ?";
        return jdbcTemplate.update(sql, chassi);
    }
}
