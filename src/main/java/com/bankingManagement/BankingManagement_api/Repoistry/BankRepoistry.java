package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepoistry extends JpaRepository<Bank,Integer> {
    // Querry method to create jpa  custom querry

    List<Bank> findByBankName(String name);
    List<Bank> findByBankAddress(String address);
    List<Bank> findByBankNameOrBankAddress(String name, String address);
    List<Bank> findByBankNameAndBankAddress(String name, String address);

    // QUERRY ANNOTATION IS USE TO CREATE JPA CUSTOM QUERRY
//    @Query("select bank from Bank bank where bankName=?")
//    List<Bank>  findByName(String bankName);

    // DELETION OPERATION
    void deleteAllByBankName(String name);
    void deleteAllByBankAddress(String address);
    void deleteAllByBankNameOrBankAddress(String name, String address);
    void deleteAllByBankNameAndBankAddress(String name, String address);
}
