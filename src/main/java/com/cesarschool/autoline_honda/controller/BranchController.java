package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Address;
import com.cesarschool.autoline_honda.domain.Branch;
import com.cesarschool.autoline_honda.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/create-with-address")
    public ResponseEntity<String> createBranchWithAddress(
            @RequestBody Map<String, Object> request) {

        // Extract Branch data
        Branch branch = new Branch();
        branch.setCnpj((String) request.get("cnpj"));
        branch.setName((String) request.get("name"));

        // Extract Address data
        Address address = new Address();
        address.setZipCode((String) request.get("zipCode"));
        address.setStreet((String) request.get("street"));
        address.setAddressNumber((Integer) request.get("addressNumber"));
        address.setNeighborhood((String) request.get("neighborhood"));
        address.setCity((String) request.get("city"));
        address.setState((String) request.get("state"));

        // Call service layer
        branchService.createBranchWithAddress(branch, address);

        return ResponseEntity.status(HttpStatus.CREATED).body("Branch and Address created successfully");
    }

//@CrossOrigin("*")
//@RestController
//@RequestMapping("/branches")
//public class BranchController {
//
//    private final BranchService branchService;
//
//    @Autowired
//    public BranchController(BranchService branchService) {
//        this.branchService = branchService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
//        try {
//            Branch createdBranch = branchService.createBranch(branch);
//            return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Branch> getBranchByCnpj(@PathVariable String cnpj) {
        Optional<Branch> branchOptional = branchService.findBranchByCnpj(cnpj);
        return branchOptional.map(branch -> new ResponseEntity<>(branch, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.findAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Branch> updateBranch(@PathVariable String cnpj, @RequestBody Branch branch) {
        branch.setCnpj(cnpj);
        try {
            Branch updatedBranch = branchService.updateBranch(branch);
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deleteBranch(@PathVariable String cnpj) {
        try {
            branchService.deleteBranchByCnpj(cnpj);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
