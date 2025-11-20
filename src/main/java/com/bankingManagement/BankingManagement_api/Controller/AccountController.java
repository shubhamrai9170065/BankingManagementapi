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
@RequestMapping("/api/v3/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccountDetails(){
        List<AccountDTO> accounts;
        try{
            accounts = accountService.getAllAccount();
            log.info("Account details is: {}",accounts);
        }catch(AccountDetailsNotFound e){
            log.error("No record exist for account");
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("some exception occur during fetching all details of account");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getByAccountId(@PathVariable("id") int id){
        AccountDTO account;
        try{
            account = accountService.getAccountById(id);
            log.info("Account details for id: {} is: {}",id,account);
        } catch(AccountDetailsNotFound e){
            log.error("No account record eixst for id: {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching the account details based on id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/accountType/{type}")
    public ResponseEntity<List<AccountDTO>> getAccountByAccountType(@PathVariable("type") String type){
        List<AccountDTO> account;
        try{
            account = accountService.getAccountByAccountType(type);
            log.info("Account details for account type: {} is: {}",type,account);
        }catch (AccountDetailsNotFound e){
            log.error("No account record exist for account type: {}",type);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching the details of account based on account type");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/balance/{balance}")
    public ResponseEntity<List<AccountDTO>> getAccountByAccountBalance(@PathVariable("balance") Double balance){
        List<AccountDTO> account;
        try{
            account = accountService.getAccountByAccountBalance(balance);
            log.info("Account details for balance: {} is: {}",balance,account);
        } catch (AccountDetailsNotFound e) {
            log.error("No account record exist for  account balance: {}",balance);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.info("Some exception occur during fetching the account by balance");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }

    @GetMapping("/typeOrBalnce/{type}/{balance}")
    public ResponseEntity<List<AccountDTO>> getAccountByTypeOrBalance(@PathVariable("type") String type,
                                                                      @PathVariable("balance") Double balance){
        List<AccountDTO> accounts;
        try{
            accounts = accountService.getAccountByTypeOrBalance(type,balance);
            log.info("Account details for type: {} or balance: {} is: {}",type,balance,accounts);
        }catch(AccountDetailsNotFound e){
            log.error("No account record exist for type: {} or balance: {}",type,balance);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during the fetching the detail based on type: {} or balance: {}",type,balance);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }

    @GetMapping("/typeAndBalance/{type}/{balance}")
    public ResponseEntity<List<AccountDTO>> getAccountByTypeAndBalance(@PathVariable("type") String type,
                                                                       @PathVariable("balance") Double balance){
        List<AccountDTO> account;
        try{
            account = accountService.getAccountByTypeAndBalance(type,balance);
            log.info("Account details for type: {} and balance: {} is: {}",type,balance,account);
        }catch(AccountDetailsNotFound e){
            log.error("No account record exist for type: {} and balance: {}",type,balance);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching account details based on type and balance");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(account,HttpStatus.OK);
    }
}
