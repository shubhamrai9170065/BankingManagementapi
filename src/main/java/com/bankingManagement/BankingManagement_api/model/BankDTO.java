package com.bankingManagement.BankingManagement_api.model;


import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
 @Getter
 @Setter
 // @JsonInclude(JsonInclude.Include.NON_NULL) is not send null to controller
 @JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDTO {
    int bankCode;
    String bankName;
    String bankAddress;
    Set<BranchDTO> branches;
    Set<AccountDTO> accounts;
}
