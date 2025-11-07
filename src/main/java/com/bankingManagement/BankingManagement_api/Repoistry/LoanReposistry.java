package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanReposistry extends JpaRepository<Loan,Integer> {
    List<Loan> findByLoanType(String loanType);
    List<Loan> findByLoanAmount(long amount);
    List<Loan> findByLoanTypeOrLoanAmount(String loantype, long amount);
    List<Loan> findByLoanTypeAndLoanAmount(String loantype, long amount);
}
