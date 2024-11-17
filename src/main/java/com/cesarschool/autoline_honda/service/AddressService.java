package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Address;
import com.cesarschool.autoline_honda.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(Address address) {
        int rowsAffected = addressRepository.saveAddress(address);
        if (rowsAffected == 1) {
            return address;
        } else {
            throw new RuntimeException("Failed to create address");
        }
    }

    public Address updateAddress(Address address) {
        int rowsAffected = addressRepository.updateAddress(address);
        if (rowsAffected == 1) {
            return address;
        } else {
            throw new RuntimeException("Failed to update address with id: " + address.getBranchCnpj());
        }
    }

    public void deleteAddressByBranchCnpj(String branchCnpj) {
        int rowsAffected = addressRepository.deleteAddressByBranchCnpj(branchCnpj);
        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete address with id: " + branchCnpj);
        }
    }

    public Optional<Address> findAddressByBranchCnpj(String branchCnpj) {
        return addressRepository.findAddressByBranchCnpj(branchCnpj);
    }

    public List<Address> findAllAddresses() {
        return addressRepository.findAllAddresses();
    }
}
