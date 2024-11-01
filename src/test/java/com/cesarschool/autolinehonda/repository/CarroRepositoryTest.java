package com.cesarschool.autolinehonda.repository;

import com.cesarschool.autolinehonda.model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@JdbcTest
@Import(CarroRepository.class)
@ActiveProfiles("test") // Usa as configurações do application-test.properties
class CarroRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CarroRepository carroRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS carro (" +
                "chassi VARCHAR(255) PRIMARY KEY, " +
                "preco INT, " +
                "cor VARCHAR(255), " +
                "roda INT, " +
                "tipo_combustivel VARCHAR(255), " +
                "fk_especificacao_PK VARCHAR(255), " +
                "ano INT, " +
                "motor VARCHAR(255), " +
                "cambio VARCHAR(255)" +
                ")");
    }

    @Test
    void testSaveAndFindById() {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        carroRepository.save(carro);

        Carro retrievedCarro = carroRepository.findById("1234ABC");
        assertNotNull(retrievedCarro);
        assertEquals("Vermelho", retrievedCarro.getCor());
    }

    @Test
    void testFindAll() {
        Carro carro1 = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        Carro carro2 = new Carro("5678DEF", 40000, "Azul", 4, "Diesel", "SPEC002", 2021, "V6", "Manual");
        carroRepository.save(carro1);
        carroRepository.save(carro2);

        List<Carro> carros = carroRepository.findAll();
        assertEquals(2, carros.size());
    }

    @Test
    void testDeleteById() {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        carroRepository.save(carro);

        carroRepository.deleteById("1234ABC");
        Carro retrievedCarro = carroRepository.findById("1234ABC");
        assertNull(retrievedCarro);
    }
}
