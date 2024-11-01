package com.cesarschool.autolinehonda.model;

import lombok.*;

@Data // Gera autiomaticamente getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Carro
{
    private String chassi;
    private int preco;
    private String cor;
    private int roda;
    private String tipoCombustivel;
    private String fkEspecificacaoPk;
    private int ano;
    private String motor;
    private String cambio;
}
