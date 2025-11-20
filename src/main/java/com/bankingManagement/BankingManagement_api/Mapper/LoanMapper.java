package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;

public class LoanMapper {

    public static LoanDTO convertLoanToLoanDTO(Loan loan){
        LoanDTO loanDto = new LoanDTO();
        loanDto.setLoanID(loan.getLoanID());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setLoanAmount(loan.getLoanAmount());
        return loanDto;
    }
}
