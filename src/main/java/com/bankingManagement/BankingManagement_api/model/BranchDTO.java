package com.bankingManagement.BankingManagement_api.model;

import com.bankingManagement.BankingManagement_api.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchDTO {
    int branchID;
    String branchName;
    String branchAddress;
    BankDTO bank;
    private Set<LoanDTO> loan;
    private Set<AccountDTO> accounts;
}
