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

    public Specification createSpecification(Specification specification) {
        specificationRepository.saveSpecification(specification);
        return specification;
    }

    public Specification updateSpecification(Specification specification) {
        if (specificationRepository.updateSpecification(specification) > 0) {
            return specification;
        }
        throw new RuntimeException("Failed to update the specification");
    }

    public void deleteSpecificationByCarChassis(String carChassis) {
        if (specificationRepository.deleteSpecificationByCarChassis(carChassis) == 0) {
            throw new RuntimeException("Failed to delete the specification of the car with: " + carChassis);
        }
    }

    public Optional<Specification> findSpecificationByCarChassis(String carChassis) {
        return specificationRepository.findSpecificationByCarChassis(carChassis);
    }

    // Find all specifications
    public List<Specification> findAllSpecifications() {
        return specificationRepository.findAllSpecifications();
    }
}
