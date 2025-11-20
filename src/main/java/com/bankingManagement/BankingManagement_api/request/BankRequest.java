package com.bankingManagement.BankingManagement_api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankRequest {
    @NonNull
    private String bankName;
    @NonNull
    private String bankAddress;
    private Set<BranchRequest> branch;
}
