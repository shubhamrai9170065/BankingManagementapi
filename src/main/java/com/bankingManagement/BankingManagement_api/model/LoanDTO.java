package com.bankingManagement.BankingManagement_api.model;

import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.bankingManagement.BankingManagement_api.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDTO {
    private int loanId;
    private String loanType;
    private double loanAmount;
    private BranchDTO branch;
    private CustomerDTO customers;
}
