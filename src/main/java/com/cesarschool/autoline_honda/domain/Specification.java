package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Specification {
    private String category;
    private String model;
    private String version;
    private String carChassis;
}