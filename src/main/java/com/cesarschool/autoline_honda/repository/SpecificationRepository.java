package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository {
    int saveSpecification(Specification specification);
    int updateSpecification(Specification specification);
    int deleteSpecificationByCarChassis(String carChassis);
    Optional<Specification> findSpecificationByCarChassis(String carChassis);
    List<Specification> findAllSpecifications();
}
