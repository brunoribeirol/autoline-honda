package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    int saveAddress(Address address);
    int updateAddress(Address address);
    int deleteAddressByBranchCnpj(String branchCnpj);
    Optional<Address> findAddressByBranchCnpj(String branchCnpj);
    List<Address> findAllAddresses();
}
