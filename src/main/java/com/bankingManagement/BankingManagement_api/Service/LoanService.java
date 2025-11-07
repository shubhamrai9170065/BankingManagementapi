package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;
import org.hibernate.annotations.LazyToOne;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getAllLoans() throws LoanDetailsNotFound;
    LoanDTO getByLaonId(int id) throws LoanDetailsNotFound;
    List<LoanDTO> getByLoanType(String loanType) throws LoanDetailsNotFound;
    List<LoanDTO> getByLoanAmount(long amount) throws LoanDetailsNotFound;
    List<LoanDTO> getByLoanTypeOrLoanAmount(String loanType, long amount) throws LoanDetailsNotFound;
    List<LoanDTO> findByLoanTypeAndLoanAmountMethod(String loantype, long amount) throws LoanDetailsNotFound;
}
