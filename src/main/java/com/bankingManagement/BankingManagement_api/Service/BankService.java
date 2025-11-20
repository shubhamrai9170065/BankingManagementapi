package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import com.bankingManagement.BankingManagement_api.request.BankRequest;
import com.bankingManagement.BankingManagement_api.updateRequest.BankUpdateRequest;

import java.util.List;

public interface BankService {
    List<BankDTO> getAllBanks() throws BankDetailsNotFound;
    BankDTO getById(int id) throws BankDetailsNotFound;
    List<BankDTO> getByName(String name) throws BankDetailsNotFound;
    List<BankDTO> getByAddress(String address) throws BankDetailsNotFound;
    List<BankDTO> getByBankNameOrBankAddress(String name, String address) throws BankDetailsNotFound;
    List<BankDTO> getByBankNameAndBankAddress(String name, String address) throws BankDetailsNotFound;

    // DELETE OPERATIONS
    String deleteBankById(int id) throws BankDetailsNotFound;
    String deleteBankByName(String name) throws BankDetailsNotFound;
    String deleteBankByAddress(String address) throws BankDetailsNotFound;
    String deleteBankByNameOrAddress(String name, String address) throws BankDetailsNotFound;
    String deleteBankByNameAndAddress(String name, String address) throws BankDetailsNotFound;

    // POST OPERATION
    BankDTO createBank(BankRequest bankRequest) throws BankDetailsNotFound;


    //PUT OPERATION
    BankDTO updateBank(BankUpdateRequest bankUpdateRequest) throws BankDetailsNotFound;
}
