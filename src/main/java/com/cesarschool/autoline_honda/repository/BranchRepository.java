package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    int saveBranch(Branch branch);
    int updateBranch(Branch branch);
    int deleteBranchByCnpj(String cnpj);
    Optional<Branch> findBranchByCnpj(String cnpj);
    List<Branch> findAllBranches();
}
