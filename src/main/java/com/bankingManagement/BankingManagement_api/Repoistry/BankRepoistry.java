package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepoistry extends JpaRepository<Bank,Integer> {
    // Querry method to create jpa  custom querry
    // in findByBankName , findBy is keyword and BankName is property of entity class Bank
    List<Bank> findByBankName(String bankName);
    List<Bank> findByBankAddress(String bankAddress);
    List<Bank> findByBankNameOrBankAddress(String name, String address);
    List<Bank> findByBankNameAndBankAddress(String name, String address);

    // QUERRY ANNOTATION IS USE TO CREATE JPA CUSTOM QUERRY
//    @Query("select bank from Bank bank where bankName=?")
//    List<Bank>  findByName(String bankName);
}
