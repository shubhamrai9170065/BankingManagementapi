package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.AccountDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount() throws AccountDetailsNotFound;
    AccountDTO getAccountById(int id) throws AccountDetailsNotFound;
    List<AccountDTO> getAccountByAccountType(String type) throws  AccountDetailsNotFound;
    List<AccountDTO> getAccountByAccountBalance(Double balance) throws AccountDetailsNotFound;
    List<AccountDTO> getAccountByTypeOrBalance(String type, Double balance) throws AccountDetailsNotFound;
    List<AccountDTO> getAccountByTypeAndBalance(String type, Double balance) throws AccountDetailsNotFound;
}
