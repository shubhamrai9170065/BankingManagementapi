package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.AccountDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.AccountService;
import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v2/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findingAllAccounts(){
        log.info("Account controller start fecthing all the accounts");
        List<AccountDTO> accounts;
        try{
            accounts = accountService.findAllAccounts();
            log.info("Accounts details is: {}", accounts);
        }catch(AccountDetailsNotFound ex){
            log.error("Account doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.info("Some exception generated while fetching the details of account");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }


    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> findByAccountNumber(@PathVariable("accountNumber") int accountNumber){
        log.info("fetching details of customer by account number in account controller and findByAccountNumber method");
        AccountDTO account;
        try{
            account = accountService.findByAccountNumber(accountNumber);
            log.info("Account details for account number is: {}",account);
        } catch(AccountDetailsNotFound ex){
            log.error("Account number doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Some exception occur while fetching the data by account number");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/accountType/{accountType}")
    public ResponseEntity<List<AccountDTO>> findByAccountType(@PathVariable("accountType") String accountType){
        log.info("Fetching of data on the basis of accountType");
        List<AccountDTO> accounts;
        try{
            accounts = accountService.findByAccountType(accountType);
            log.info("The details of account is: {}",accounts);
        }catch(AccountDetailsNotFound ex){
            log.error("No account exist for account type: {}",accountType);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("some exception occur during fetching the data by accountType");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }
}
