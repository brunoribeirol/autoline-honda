package com.cesarschool.autolinehonda.controller;

import com.cesarschool.autolinehonda.model.Carro;
import com.cesarschool.autolinehonda.service.CarroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService carroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCarros() throws Exception {
        Carro carro1 = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        Carro carro2 = new Carro("5678DEF", 40000, "Azul", 4, "Diesel", "SPEC002", 2021, "V6", "Manual");
        List<Carro> carros = Arrays.asList(carro1, carro2);

        when(carroService.findAll()).thenReturn(carros);

        mockMvc.perform(get("/api/carros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(carros.size()))
                .andExpect(jsonPath("$[0].chassi").value("1234ABC"))
                .andExpect(jsonPath("$[1].cor").value("Azul"));
    }

    @Test
    void testGetCarroById() throws Exception {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        when(carroService.findById("1234ABC")).thenReturn(carro);

        mockMvc.perform(get("/api/carros/1234ABC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chassi").value("1234ABC"))
                .andExpect(jsonPath("$.cor").value("Vermelho"));
    }

    @Test
    void testCreateCarro() throws Exception {
        Carro carro = new Carro("1234ABC", 50000, "Vermelho", 4, "Gasolina", "SPEC001", 2022, "V8", "Automático");
        when(carroService.save(any(Carro.class))).thenReturn(carro);

        mockMvc.perform(post("/api/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.chassi").value("1234ABC"));
    }

    @Test
    void testDeleteCarro() throws Exception {
        doNothing().when(carroService).deleteById("1234ABC");

        mockMvc.perform(delete("/api/carros/1234ABC"))
                .andExpect(status().isNoContent());
    }
}
