package com.bankingManagement.BankingManagement_api.updateRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankUpdateRequest {
    private int bankCode;
    private String bankName;
    private String bankAddress;
}
