package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;

public class AccountMapper {

    public static AccountDTO convertAccountToAccountDTO(Account account) {
        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setAccountBalance(account.getAccountBalance());
        return accountDto;
    }
}
