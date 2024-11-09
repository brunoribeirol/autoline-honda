package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    // Save a new address
    int saveAddress(Address address);

    // Update an existing address
    int updateAddress(Address address);

    // Delete an address by its primary key
    int deleteAddressByBranchCnpj(String branchCnpj);

    // Find an address by its primary key
    Optional<Address> findAddressByBranchCnpj(String branchCnpj);

    // Find all addresses
    List<Address> findAllAddresses();
}
