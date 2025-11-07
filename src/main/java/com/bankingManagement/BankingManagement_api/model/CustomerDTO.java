package com.bankingManagement.BankingManagement_api.model;

import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private long customerPhone;
    private Set<LoanDTO> loans;
    private Set<AccountDTO> accounts;
}
