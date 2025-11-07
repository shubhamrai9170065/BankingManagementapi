package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.AccountDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.AccountMapper;
import com.bankingManagement.BankingManagement_api.Mapper.BankMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.AccountRepoistry;
import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepoistry accountRepoistry;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<AccountDTO> findAllAccounts() throws AccountDetailsNotFound {
        log.info("Fetching details of all account");
        List<Account> account = accountRepoistry.findAll();
        if(CollectionUtils.isEmpty(account)){
            log.error("Account is not exist");
            throw new AccountDetailsNotFound("Account details not exist");
        }
        log.info("Fetching operation successfully executed without any execution");
        return account.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
    }

    public AccountDTO findByAccountNumber(int id) throws AccountDetailsNotFound {
        log.info("Fetching account details on basis their account number");
        Optional<Account> account = accountRepoistry.findById(id);
        if(account.isEmpty()){
            log.error("Account number doesn't exist");
            throw new AccountDetailsNotFound("Account number doesn't exist");
        }
        return AccountMapper.convertAccountToAccountDTO(account.get());
    }

   public  List<AccountDTO> findByAccountType(String accountType) throws AccountDetailsNotFound {
        log.info("fetching the data on the basis of accountType");
        List<Account> account = accountRepoistry.findByAccountType(accountType);
        if(CollectionUtils.isEmpty(account)){
            log.error("No account exist for type: {}",accountType);
            throw new AccountDetailsNotFound("No account exist");
        }
        return account.stream().map(AccountMapper::convertAccountToAccountDTO).collect(Collectors.toList());
    }
}
