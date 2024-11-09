package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    // Save a new branch
    int saveBranch(Branch branch);

    // Update an existing branch
    int updateBranch(Branch branch);

    // Delete a branch by its primary key
    int deleteBranchByCnpj(String cnpj);

    // Find a branch by its primary key
    Optional<Branch> findBranchByCnpj(String cnpj);

    // Find all branches
    List<Branch> findAllBranches();
}
