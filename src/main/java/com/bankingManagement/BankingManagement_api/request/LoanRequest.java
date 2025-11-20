package com.bankingManagement.BankingManagement_api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanRequest {
    @NonNull
    private String loanType;
    private double loanAmount;
    private AccountRequest account;
}
