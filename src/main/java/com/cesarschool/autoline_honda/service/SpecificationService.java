package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Specification;
import com.cesarschool.autoline_honda.repository.SpecificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationService {

    private final SpecificationRepository specificationRepository;

    public SpecificationService(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    // Create a new specification
    public Specification createSpecification(Specification specification) {
        specificationRepository.saveSpecification(specification);
        return specification;
    }

    // Update an existing specification
    public Specification updateSpecification(Specification specification) {
        if (specificationRepository.updateSpecification(specification) > 0) {
            return specification;
        }
        throw new RuntimeException("Failed to update the specification");
    }

    // Delete a specification by category and model
    public void deleteSpecification(String category, String model) {
        if (specificationRepository.deleteSpecification(category, model) == 0) {
            throw new RuntimeException("Failed to delete the specification with category: " + category + " and model: " + model);
        }
    }

    // Find a specification by category and model
    public Optional<Specification> findSpecificationByCategoryAndModel(String category, String model) {
        return specificationRepository.findSpecificationByCategoryAndModel(category, model);
    }

    // Find all specifications
    public List<Specification> findAllSpecifications() {
        return specificationRepository.findAllSpecifications();
    }
}
