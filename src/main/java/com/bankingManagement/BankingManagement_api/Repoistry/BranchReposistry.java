package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Branch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BranchReposistry extends JpaRepository<Branch, Integer> {

    List<Branch> findByBranchName(String name);

    List<Branch> findByBranchAddressOrBranchID(String address, int id);
    List<Branch> findByBranchNameAndBranchAddress(String branchName, String address);

    @Transactional
    int deleteAllByBranchName(String name);  // returns count of deleted rows
}
