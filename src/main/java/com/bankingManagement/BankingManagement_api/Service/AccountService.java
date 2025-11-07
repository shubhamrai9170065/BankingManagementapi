package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.AccountDetailsNotFound;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import java.util.List;

public interface AccountService {
    List<AccountDTO> findAllAccounts() throws AccountDetailsNotFound;
    AccountDTO findByAccountNumber(int id) throws AccountDetailsNotFound;
    List<AccountDTO> findByAccountType(String accountType) throws AccountDetailsNotFound;
}
