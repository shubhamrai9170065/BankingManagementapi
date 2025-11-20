package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import com.bankingManagement.BankingManagement_api.request.BankRequest;

public class BankMapper {

    public static BankDTO convertBankToBankTo(Bank bank){
        BankDTO bankDto = new BankDTO();
        bankDto.setBankCode(bank.getBankCode());
        bankDto.setBankName(bank.getBankName());
        bankDto.setBankAddress(bank.getBankAddress());
        return bankDto;
    }

    public static Bank convertBankRequestToBank(BankRequest bankRequest){
        Bank bank = new Bank();
        bank.setBankName(bankRequest.getBankName());
        bank.setBankAddress(bankRequest.getBankAddress());
        return bank;
    }
}
