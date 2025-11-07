package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.model.BankDTO;

import java.util.List;

public interface BankService {
    List<BankDTO> findAllBanks() throws BankDetailsNotFound;
    BankDTO findBankById(int bankCode) throws BankDetailsNotFound;
    List<BankDTO> findBankByNameMethod(String bankName) throws BankDetailsNotFound;
    List<BankDTO> findBankByAddressMethod(String bankAddress) throws BankDetailsNotFound;
    List<BankDTO> findBanknameOrBankAddressMethod(String name,String address) throws BankDetailsNotFound;
    List<BankDTO> findByBankNameAndBankAddressMethod(String name, String address) throws BankDetailsNotFound;
}
