package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.AccountDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.AccountMapper;
import com.bankingManagement.BankingManagement_api.Mapper.BranchMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.AccountRepoistry;
import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepoistry accountRepoistry;

    public List<AccountDTO> getAllAccount() throws AccountDetailsNotFound {
        log.info("Fetching all details of account in service layer");
        List<Account> accounts = accountRepoistry.findAll();
        if(CollectionUtils.isEmpty(accounts)){
            log.error("No account details exist");
            throw new AccountDetailsNotFound("NO account record exist");
        }
        List<AccountDTO> accountDto = accounts.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
        return accountDto;
    }

    public AccountDTO getAccountById(int id) throws AccountDetailsNotFound {
        log.info("Fetching details of account by id: {}",id);
        Optional<Account> account = accountRepoistry.findById(id);
        if(account.isEmpty()){
            String str = "No account record exist for id: " + id;
            log.info(str);
            throw new AccountDetailsNotFound(str);
        }

        return AccountMapper.convertAccountToAccountDTO(account.get());
    }

    public List<AccountDTO> getAccountByAccountType(String type) throws  AccountDetailsNotFound {
        log.info("Fetching details of account by account type: {}",type);
        List<Account> accounts = accountRepoistry.findByAccountType(type);
        if(CollectionUtils.isEmpty(accounts)){
            String str = "No account record exist for account type: " + type;
            log.error(str);
            throw new AccountDetailsNotFound(str);
        }
        List<AccountDTO> accountDto = accounts.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
        return accountDto;
    }

    public List<AccountDTO> getAccountByAccountBalance(Double balance) throws AccountDetailsNotFound {
        log.info("Fetching details of account by account balance: {}",balance);
        List<Account> accounts = accountRepoistry.findByAccountBalance(balance);
        if(CollectionUtils.isEmpty(accounts)){
            String str = "No account record exist for balance: {}" + balance;
            log.info(str);
            throw new AccountDetailsNotFound(str);
        }
        List<AccountDTO> accountDto = accounts.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
        return accountDto;
    }

    public List<AccountDTO> getAccountByTypeOrBalance(String type, Double balance) throws AccountDetailsNotFound {
        log.info("Fetching details of account based on type or balance");
        List<Account> accounts = accountRepoistry.findByAccountTypeOrAccountBalance(type,balance);
        if(CollectionUtils.isEmpty(accounts)){
            String str = "No record exist for type: " + type + " or balance: " + balance;
            log.info(str);
            throw  new AccountDetailsNotFound(str);
        }
        List<AccountDTO> accountsDto = accounts.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
        return accountsDto;
    }

    public List<AccountDTO> getAccountByTypeAndBalance(String type, Double balance) throws AccountDetailsNotFound {
        log.info("Fetching details of account based on type: {} and balnce: {}",type,balance);
        List<Account> accounts = accountRepoistry.findByAccountTypeAndAccountBalance(type,balance);
        if(CollectionUtils.isEmpty(accounts)){
            String str = "No record exist for type: " + type + " and balance: {}" + balance;
            log.info(str);
            throw new AccountDetailsNotFound(str);
        }
        List<AccountDTO> account = accounts.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
        return account;
    }
}
