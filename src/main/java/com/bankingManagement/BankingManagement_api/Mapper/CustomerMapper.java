package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Account;
import com.bankingManagement.BankingManagement_api.entity.Customer;
import com.bankingManagement.BankingManagement_api.model.AccountDTO;
import com.bankingManagement.BankingManagement_api.model.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerAddress(customer.getCustomerAddress());
        customerDTO.setCustomerPhone(customer.getCustomerPhone());

        Set<AccountDTO> accountDetails = (customer.getAccounts() == null)
                ? Collections.emptySet()
                : customer.getAccounts()
                .stream()
                .map(this::convertAccountToAccountDTO)
                .collect(Collectors.toSet());

        customerDTO.setAccounts(accountDetails);
        return customerDTO;
    }

    private AccountDTO convertAccountToAccountDTO(Account account) {
        if (account == null) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setAccountBalance(account.getAccountBalance());
        return accountDTO;
    }
}
