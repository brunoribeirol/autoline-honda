package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository {

    // Save a new specification
    int saveSpecification(Specification specification);

    // Update an existing specification
    int updateSpecification(Specification specification);

    // Delete a specification by chassis
    int deleteSpecificationByCarChassis(String carChassis);

    // Find a specification by chassis
    Optional<Specification> findSpecificationByCarChassis(String carChassis);

    // Find all specifications
    List<Specification> findAllSpecifications();
}
