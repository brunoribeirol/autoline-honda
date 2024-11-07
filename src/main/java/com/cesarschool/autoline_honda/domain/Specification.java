package com.cesarschool.autoline_honda.domain;

import lombok.*;

@Data // Automatically generates getters, setters, toString, equals e hashCode
@AllArgsConstructor
@NoArgsConstructor

public class Specification
{
    private String specification_PK;
    private String category;
    private String model;
    private String version;
}
