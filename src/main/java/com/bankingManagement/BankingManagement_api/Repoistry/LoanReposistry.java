package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanReposistry extends JpaRepository<Loan,Integer> {
    List<Loan> findByLoanType(String type);
}
