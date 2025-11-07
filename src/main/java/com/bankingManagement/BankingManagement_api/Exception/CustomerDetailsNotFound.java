package com.bankingManagement.BankingManagement_api.Exception;

public class CustomerDetailsNotFound extends Exception {

    public CustomerDetailsNotFound(){
        super();
    }
    public CustomerDetailsNotFound(String message){
        super(message);
    }
}
