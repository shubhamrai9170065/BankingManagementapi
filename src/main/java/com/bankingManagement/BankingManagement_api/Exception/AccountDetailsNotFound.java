package com.bankingManagement.BankingManagement_api.Exception;

public class AccountDetailsNotFound extends Exception{

    public AccountDetailsNotFound(){
        super();
    }

    public AccountDetailsNotFound(String message){
        super(message);
    }
}
