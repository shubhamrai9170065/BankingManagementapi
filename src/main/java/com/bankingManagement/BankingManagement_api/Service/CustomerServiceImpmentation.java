package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.CustomerDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.CustomerMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.CustomerReposistory;
import com.bankingManagement.BankingManagement_api.entity.Customer;
import com.bankingManagement.BankingManagement_api.model.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomerServiceImpmentation implements CustomerService {

    @Autowired
    private CustomerReposistory customerReposistory;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers() throws CustomerDetailsNotFound {
        log.info("Fetching all customer details from the repository");
        List<Customer> customerList = customerReposistory.findAll();

        if (CollectionUtils.isEmpty(customerList)) {
            log.info("Customer details not exist");
            throw new CustomerDetailsNotFound("Customer details not found");
        }
        return customerList.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }


    public CustomerDTO getCustomerById(int customerId) throws CustomerDetailsNotFound {
        log.info("Fecthing data based on customer id: {}", customerId);
        Optional<Customer> customer = customerReposistory.findById(customerId);
        if (customer.isEmpty()) {
            log.error("Customer id doesn't exist");
            throw new CustomerDetailsNotFound("Customer id not found");
        }
        return customerMapper.convertCustomerToCustomerDTO(customer.get());
    }

    public List<CustomerDTO> getCustomerByNameMethod(String customerName) throws CustomerDetailsNotFound {
        log.info("Fetching customer details based on customer name: {}", customerName);
        List<Customer> customers = customerReposistory.findByCustomerName(customerName);
        if (CollectionUtils.isEmpty(customers)) {
            log.error(" Customer name doesn't exist");
            throw new CustomerDetailsNotFound("Customer details not found for the name: " + customerName);
        }
        return customers.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }
     @Override
    public List<CustomerDTO> getCustomerByAddressMethod(String customerAddress) throws CustomerDetailsNotFound {
        log.info("Fetching customer details based on customer address: {}", customerAddress);
        List<Customer> customers = customerReposistory.findByCustomerAddress(customerAddress);
        if(CollectionUtils.isEmpty(customers)){
            log.error("Customer address doesn't exist");
            throw new CustomerDetailsNotFound("Customer details not found for the address: " + customerAddress);
        }
        return customers.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerByPhoneNumber(long phoneNumber) throws CustomerDetailsNotFound {
        log.info("Fetching customer details base on phone number: {}", phoneNumber);
        Customer customer = customerReposistory.findByCustomerPhone(phoneNumber);
        if (customer == null) {

            log.error("Customer phone number doesn't exist");
            throw new CustomerDetailsNotFound("Customer phone number is not registered: " + phoneNumber);
        }
            return customerMapper.convertCustomerToCustomerDTO(customer);
        }
    public List<CustomerDTO> getCustomerByCustomerNameOrCustomerAddress(String name, String address) throws CustomerDetailsNotFound{
        log.info("Fetching data on the basis of customer name and customer address");
        List<Customer> customer = customerReposistory. findByCustomerNameOrCustomerAddress(name,address);
        if(CollectionUtils.isEmpty(customer)){
            log.info("No data match with customer name or customer address");
            throw new CustomerDetailsNotFound("Customer details not found ont the basis of Customer name or customer address");
        }
        return customer.stream().map(customerMapper::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }
}
