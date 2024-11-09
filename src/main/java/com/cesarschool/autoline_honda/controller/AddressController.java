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
@RequestMapping("/addresses")  // Define the base URL for all address-related endpoints
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Endpoint to create a new address
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        try {
            Address createdAddress = addressService.createAddress(address);
            return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);  // Return status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to get an address by its primary key (address_pk)
    @GetMapping("/{branchCnpj}")
    public ResponseEntity<Address> getAddressByBranchCnpj(@PathVariable String branchCnpj) {
        Optional<Address> addressOptional = addressService.findAddressByBranchCnpj(branchCnpj);
        return addressOptional.map(address -> new ResponseEntity<>(address, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if not found
    }

    // Endpoint to get all addresses
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.findAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);  // Return status 200
    }

    // Endpoint to update an address
    @PutMapping("/{branchCnpj}")
    public ResponseEntity<Address> updateAddress(@PathVariable String branchCnpj, @RequestBody Address address) {
        address.setBranchCnpj(branchCnpj);  // Ensure the branchCnpj is set in the address object
        try {
            Address updatedAddress = addressService.updateAddress(address);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);  // Return status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to delete an address by its primary key (address_pk)
    @DeleteMapping("/{branchCnpj}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String branchCnpj) {
        try {
            addressService.deleteAddressByBranchCnpj(branchCnpj);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return status 204 if deletion was successful
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
