package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Specification;
import com.cesarschool.autoline_honda.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specifications")  // Base URL for all specification-related endpoints
public class SpecificationController {

    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    // Endpoint to create a new specification
    @PostMapping
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {
        try {
            Specification createdSpecification = specificationService.createSpecification(specification);
            return new ResponseEntity<>(createdSpecification, HttpStatus.CREATED); // Return status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to get a specification by category and model
    @GetMapping("/{carChassis}")
    public ResponseEntity<Specification> getSpecificationByCarChassis(@PathVariable String carChassis) {
        Optional<Specification> specificationOptional = specificationService.findSpecificationByCarChassis(carChassis);
        return specificationOptional.map(specification -> new ResponseEntity<>(specification, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if not found
    }

    // Endpoint to get all specifications
    @GetMapping
    public ResponseEntity<List<Specification>> getAllSpecifications() {
        List<Specification> specifications = specificationService.findAllSpecifications();
        return new ResponseEntity<>(specifications, HttpStatus.OK);  // Return status 200
    }

    // Endpoint to update a specification by category and model
    @PutMapping("/{carChassis}")
    public ResponseEntity<Specification> updateSpecification(@PathVariable String carChassis,
                                                             @RequestBody Specification specification) {
        specification.setCategory(carChassis);  // Ensure category and model are set in the specification object
        try {
            Specification updatedSpecification = specificationService.updateSpecification(specification);
            return new ResponseEntity<>(updatedSpecification, HttpStatus.OK);  // Return status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to delete a specification by category and model
    @DeleteMapping("/{carChassis}")
    public ResponseEntity<Void> deleteSpecificationByCarChassis(@PathVariable String carChassis) {
        try {
            specificationService.deleteSpecificationByCarChassis(carChassis);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return status 204 if deletion was successful
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
