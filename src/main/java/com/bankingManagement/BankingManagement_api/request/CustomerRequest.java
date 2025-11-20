package com.bankingManagement.BankingManagement_api.request;

import com.bankingManagement.BankingManagement_api.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequest {
    @NonNull
    private String customerName;


    private long customerPhone;

    @NonNull
    private String customerAddress;

    private BranchRequest branch;
    private Set<AccountRequest> accounts;
}
