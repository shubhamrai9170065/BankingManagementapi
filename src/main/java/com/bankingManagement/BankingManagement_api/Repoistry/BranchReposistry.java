package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BranchReposistry extends JpaRepository<Branch, Integer> {

    // GET OPERATION METHOD
    List<Branch> findByBranchName(String name);
    List<Branch> findByBranchAddress(String address);
    List<Branch> findByBranchNameOrBranchAddress(String name, String address);
    List<Branch> findByBranchNameAndBranchAddress(String name, String address);

    // DELETION OPERATION METHOD
    void deleteAllByBranchName(String name);
    void deleteAllByBranchAddress(String address);
    void deleteAllByBranchNameOrBranchAddress(String name, String address);
}
