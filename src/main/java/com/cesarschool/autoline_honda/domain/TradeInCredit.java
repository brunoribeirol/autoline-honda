package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class TradeInCredit {
    private int tradeId;
    private String customerCpf;
    private String usedCarChassis;
    private float value;
}
