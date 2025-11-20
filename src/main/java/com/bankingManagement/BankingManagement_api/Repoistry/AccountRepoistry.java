package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepoistry extends JpaRepository<Account, Integer> {
    List<Account> findByAccountType(String type);
    List<Account> findByAccountBalance(Double balance);
    List<Account> findByAccountTypeOrAccountBalance(String type,Double balance);
    List<Account> findByAccountTypeAndAccountBalance(String type, Double balance);
}
