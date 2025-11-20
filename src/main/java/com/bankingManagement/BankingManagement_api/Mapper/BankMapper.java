package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;

public class BankMapper {

    public static BankDTO convertBankToBankTo(Bank bank){
        BankDTO bankDto = new BankDTO();
        bankDto.setBankCode(bank.getBankCode());
        bankDto.setBankName(bank.getBankName());
        bankDto.setBankAddress(bank.getBankAddress());
        return bankDto;
    }

}
