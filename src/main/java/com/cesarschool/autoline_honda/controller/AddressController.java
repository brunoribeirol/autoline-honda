package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Address;
import com.cesarschool.autoline_honda.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            Address createdAddress = addressService.createAddress(address);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{branchCnpj}")
    public ResponseEntity<Address> getAddressByBranchCnpj(@PathVariable String branchCnpj) {
        Optional<Address> addressOptional = addressService.findAddressByBranchCnpj(branchCnpj);
        return addressOptional.map(address -> new ResponseEntity<>(address, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.findAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PutMapping("/{branchCnpj}")
    public ResponseEntity<Address> updateAddress(@PathVariable String branchCnpj, @RequestBody Address address) {
        address.setBranchCnpj(branchCnpj);
        try {
            Address updatedAddress = addressService.updateAddress(address);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{branchCnpj}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String branchCnpj) {
        try {
            addressService.deleteAddressByBranchCnpj(branchCnpj);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
