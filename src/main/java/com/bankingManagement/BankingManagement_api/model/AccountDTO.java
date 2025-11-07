package com.bankingManagement.BankingManagement_api.model;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.bankingManagement.BankingManagement_api.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private int accountNumber;
    private String accountType;
    private double accountBalance;
    private BranchDTO branch;
    private CustomerDTO customers;
    private BankDTO bank;
}
