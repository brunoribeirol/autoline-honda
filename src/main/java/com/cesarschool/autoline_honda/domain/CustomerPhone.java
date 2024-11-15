package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPhone {
    private String customerCpf;
    private String phoneNumber;
}