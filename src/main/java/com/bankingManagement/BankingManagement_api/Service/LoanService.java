package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getAlLoans() throws LoanDetailsNotFound;
    LoanDTO getLoanById(int id) throws LoanDetailsNotFound;
    List<LoanDTO> getLoanByType(String type) throws  LoanDetailsNotFound;
}
