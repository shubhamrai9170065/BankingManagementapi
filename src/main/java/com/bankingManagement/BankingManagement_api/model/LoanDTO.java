package com.bankingManagement.BankingManagement_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDTO {

    private Long loanID;
    private String loanType;
    private Double loanAmount;

    private Long branchId;
    private Integer customerId;
}
