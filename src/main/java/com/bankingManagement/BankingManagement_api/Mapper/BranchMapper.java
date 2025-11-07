package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.*;
import com.bankingManagement.BankingManagement_api.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class BranchMapper {

    public static BranchDTO convertBranchToBranchDTO(Branch branch){
        BranchDTO newBranch = new BranchDTO();
        newBranch.setBranchID(branch.getBranchID());
        newBranch.setBranchName(branch.getBranchName());
        newBranch.setBranchAddress(branch.getBranchAddress());
        newBranch.setBank(convertBankToBankDTO(branch.getBank()));

        Set<LoanDTO> dto = branch.getLoan().stream().map(BranchMapper::convertLaonToLaonDTO).collect(Collectors.toSet());
        newBranch.setLoan(dto);
        return newBranch;
    }
    public static BankDTO convertBankToBankDTO(Bank bank) {
        BankDTO newBank = new BankDTO();
        newBank.setBankCode(bank.getBankCode());
        newBank.setBankName(bank.getBankName());
        newBank.setBankAddress(bank.getBankAddress());
        return newBank;
    }

    public static LoanDTO convertLaonToLaonDTO(Loan loan){
        LoanDTO newloan = new LoanDTO();
        newloan.setLoanId(loan.getLoanId());
        newloan.setLoanType(loan.getLoanType());
        newloan.setLoanAmount(loan.getLoanAmount());
        newloan.setCustomers(convertCustomerToCustomerDTO(loan.getCustomers()));
        return newloan;
    }

    public static CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerAddress(customer.getCustomerAddress());
        customerDto.setCustomerPhone(customer.getCustomerPhone());
        Set<AccountDTO> accounts = customer.getAccounts().stream().map(BranchMapper::convertAccoutToAccountDTO).collect(Collectors.toSet());
        customerDto.setAccounts(accounts);
        return customerDto;
    }

    public static AccountDTO convertAccoutToAccountDTO(Account account){
        AccountDTO accountdto = new AccountDTO();
        accountdto.setAccountNumber(account.getAccountNumber());
        accountdto.setAccountType(account.getAccountType());
        accountdto.setAccountBalance(account.getAccountBalance());
        return accountdto;
    }
}
