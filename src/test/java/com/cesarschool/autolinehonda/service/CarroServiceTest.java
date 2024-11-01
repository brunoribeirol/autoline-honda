package com.cesarschool.autolinehonda.service;

import com.cesarschool.autolinehonda.model.Carro;
import com.cesarschool.autolinehonda.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarroServiceTest {

    @Autowired
    private CarroService carroService;

    @MockBean
    private CarroRepository carroRepository;

    @Test
    void testFindById() {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        when(carroRepository.findById("1234ABC")).thenReturn(carro);

        Carro foundCarro = carroService.findById("1234ABC");
        assertNotNull(foundCarro);
        assertEquals("Vermelho", foundCarro.getCor());
    }

    @Test
    void testSave() {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        when(carroRepository.save(carro)).thenReturn(carro);

        Carro savedCarro = carroService.save(carro);
        assertNotNull(savedCarro);
        assertEquals("1234ABC", savedCarro.getChassi());
        verify(carroRepository, times(1)).save(carro);
    }
}
