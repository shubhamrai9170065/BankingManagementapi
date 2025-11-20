package com.bankingManagement.BankingManagement_api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequest {
    @NonNull
    private String accountType;

    private double accountBalance;
    private CustomerRequest customer;
    private Set<LoanRequest> loan;
}
