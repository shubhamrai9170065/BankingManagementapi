package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.CustomerDetailsNotFound;
import com.bankingManagement.BankingManagement_api.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers() throws CustomerDetailsNotFound;
    CustomerDTO getCustomerById(int customerId) throws CustomerDetailsNotFound;
    List<CustomerDTO> getCustomerByNameMethod(String customerName) throws CustomerDetailsNotFound;
    List<CustomerDTO> getCustomerByAddressMethod(String customerAddress) throws CustomerDetailsNotFound;
    CustomerDTO getCustomerByPhoneNumber(long phoneNumber) throws CustomerDetailsNotFound;
    List<CustomerDTO> getCustomerByCustomerNameOrCustomerAddress(String name, String address) throws CustomerDetailsNotFound;
}
