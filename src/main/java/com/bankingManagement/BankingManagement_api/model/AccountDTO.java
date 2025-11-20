package com.bankingManagement.BankingManagement_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private Integer accountNumber;
    private String accountType;
    private Double accountBalance;

    private Long branchId;
    private Integer customerId;
}
