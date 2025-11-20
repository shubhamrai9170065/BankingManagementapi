package com.bankingManagement.BankingManagement_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    private Integer customerId;
    private String customerName;
    private Long customerPhone;
    private String customerAddress;

    private Set<AccountDTO> accounts;
    private Set<LoanDTO> loans;
}
