package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.*;
import com.bankingManagement.BankingManagement_api.model.*;
import org.bson.LazyBSONObject;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public static LoanDTO convertLoanToLoanDTO(Loan loan){
        LoanDTO newloan = new LoanDTO();
        newloan.setLoanId(loan.getLoanId());
        newloan.setLoanType(loan.getLoanType());
        newloan.setLoanAmount(loan.getLoanAmount());
        newloan.setBranch(convertBranchToBranchDTO(loan.getBranch()));
        newloan.setCustomers(convertCustomerToCustomerDTO(loan.getCustomers()));
        return newloan;
    }
    public static BranchDTO convertBranchToBranchDTO(Branch branch){
        BranchDTO branchdto = new BranchDTO();
        branchdto.setBranchID(branch.getBranchID());
        branchdto.setBranchName(branch.getBranchName());
        branchdto.setBranchAddress(branch.getBranchAddress());
        branchdto.setBank(convertBankToBankDTO(branch.getBank()));
        Set<AccountDTO> accounts = branch.getAccount().stream().map(LoanMapper::convertAccountToAccountDTO).collect(Collectors.toSet());
        branchdto.setAccounts(accounts);
        return branchdto;
    }
    public static BankDTO convertBankToBankDTO(Bank bank){
        BankDTO bankdto = new BankDTO();
        bankdto.setBankCode(bank.getBankCode());
        bankdto.setBankName(bank.getBankName());
        bankdto.setBankAddress(bank.getBankAddress());
        return bankdto;
    }

    public static AccountDTO convertAccountToAccountDTO(Account account){
        AccountDTO accountdto = new AccountDTO();
        accountdto.setAccountNumber(account.getAccountNumber());
        accountdto.setAccountType(account.getAccountType());
        accountdto.setAccountBalance(account.getAccountBalance());
        return accountdto;

    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerdto = new CustomerDTO();
        customerdto.setCustomerId(customer.getCustomerId());
        customerdto.setCustomerName(customer.getCustomerName());
        customerdto.setCustomerPhone(customer.getCustomerPhone());
        customerdto.setCustomerAddress(customer.getCustomerAddress());
        return customerdto;
    }
}
