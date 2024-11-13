package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository {

    // Save a new specification
    int saveSpecification(Specification specification);

    // Update an existing specification
    int updateSpecification(Specification specification);

    // Delete a specification by category and model (or chassis)
    int deleteSpecification(String category, String model);

    // Find a specification by category and model (or chassis)
    Optional<Specification> findSpecificationByCategoryAndModel(String category, String model);

    // Find all specifications
    List<Specification> findAllSpecifications();
}
