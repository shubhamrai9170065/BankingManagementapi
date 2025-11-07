package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.*;
import com.bankingManagement.BankingManagement_api.model.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public static AccountDTO convertAccountToAccountDTO(Account account) {
        AccountDTO accountdto = new AccountDTO();
        accountdto.setAccountNumber(account.getAccountNumber());
        accountdto.setAccountType(account.getAccountType());
        accountdto.setAccountBalance(account.getAccountBalance());
        accountdto.setBranch(convertBranchToBranchDTO(account.getBranch()));
        accountdto.setCustomers(convertCustomerToCustomerDTO(account.getCustomers()));
        accountdto.setBank(convertBankToBankDTO(account.getBank()));
        return accountdto;
    }
    public static BranchDTO convertBranchToBranchDTO(Branch branch){
        BranchDTO branchdto = new BranchDTO();
        branchdto.setBranchID(branch.getBranchID());
        branchdto.setBranchName(branch.getBranchName());
        branchdto.setBranchAddress(branch.getBranchAddress());
        return branchdto;
    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerdto = new CustomerDTO();
        customerdto.setCustomerId(customer.getCustomerId());
        customerdto.setCustomerName(customer.getCustomerName());
        customerdto.setCustomerAddress(customer.getCustomerAddress());
        customerdto.setCustomerPhone(customer.getCustomerPhone());
        Set<LoanDTO> loans = customer.getLoans().stream().map(AccountMapper::convertLaonToLoanDTO).collect(Collectors.toSet());
        customerdto.setLoans(loans);
        return customerdto;
    }

    public static LoanDTO convertLaonToLoanDTO(Loan loan){
        LoanDTO loandto = new LoanDTO();
        loandto.setLoanId(loan.getLoanId());
        loandto.setLoanAmount(loan.getLoanAmount());
        loandto.setLoanType(loan.getLoanType());
        return loandto;
    }


    public static BankDTO convertBankToBankDTO(Bank bank){
        BankDTO bankdto = new BankDTO();
        bankdto.setBankCode(bank.getBankCode());
        bankdto.setBankName(bank.getBankName());
        bankdto.setBankAddress(bank.getBankAddress());
        return bankdto;
    }

}
