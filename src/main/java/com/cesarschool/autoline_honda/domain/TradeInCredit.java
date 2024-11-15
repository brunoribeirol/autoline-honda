package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TradeInCredit {
    private int tradeId;
    private String customerCpf;
    private String usedCarChassis;
    private float value;
}
