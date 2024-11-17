package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Branch;
import com.cesarschool.autoline_honda.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch createBranch(Branch branch) {
        int rowsAffected = branchRepository.saveBranch(branch);
        if (rowsAffected == 1) {
            return branch;
        } else {
            throw new RuntimeException("Failed to create branch");
        }
    }

    public Branch updateBranch(Branch branch) {
        int rowsAffected = branchRepository.updateBranch(branch);
        if (rowsAffected == 1) {
            return branch;
        } else {
            throw new RuntimeException("Failed to update branch with CNPJ: " + branch.getCnpj());
        }
    }

    public void deleteBranchByCnpj(String cnpj) {
        int rowsAffected = branchRepository.deleteBranchByCnpj(cnpj);
        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete branch with CNPJ: " + cnpj);
        }
    }

    public Optional<Branch> findBranchByCnpj(String cnpj) {
        return branchRepository.findBranchByCnpj(cnpj);
    }

    public List<Branch> findAllBranches() {
        return branchRepository.findAllBranches();
    }
}
