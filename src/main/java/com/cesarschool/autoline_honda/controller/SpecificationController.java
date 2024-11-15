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
@RequestMapping("/specifications")
public class SpecificationController {

    private final SpecificationService specificationService;

    @Autowired
    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @PostMapping
    public ResponseEntity<Specification> createSpecification(@RequestBody Specification specification) {
        try {
            Specification createdSpecification = specificationService.createSpecification(specification);
            return new ResponseEntity<>(createdSpecification, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{carChassis}")
    public ResponseEntity<Specification> getSpecificationByCarChassis(@PathVariable String carChassis) {
        Optional<Specification> specificationOptional = specificationService.findSpecificationByCarChassis(carChassis);
        return specificationOptional.map(specification -> new ResponseEntity<>(specification, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Specification>> getAllSpecifications() {
        List<Specification> specifications = specificationService.findAllSpecifications();
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }

    @PutMapping("/{carChassis}")
    public ResponseEntity<Specification> updateSpecification(@PathVariable String carChassis,
                                                             @RequestBody Specification specification) {
        specification.setCategory(carChassis);
        try {
            Specification updatedSpecification = specificationService.updateSpecification(specification);
            return new ResponseEntity<>(updatedSpecification, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{carChassis}")
    public ResponseEntity<Void> deleteSpecificationByCarChassis(@PathVariable String carChassis) {
        try {
            specificationService.deleteSpecificationByCarChassis(carChassis);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
