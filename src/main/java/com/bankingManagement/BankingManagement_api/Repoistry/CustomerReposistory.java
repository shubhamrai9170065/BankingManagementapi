package com.bankingManagement.BankingManagement_api.Repoistry;

import com.bankingManagement.BankingManagement_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerReposistory extends JpaRepository<Customer,Integer> {
    List<Customer> findByCustomerName(String customerName);
    List<Customer> findByCustomerAddress(String customerAddress);
    Customer findByCustomerPhone(long phone);
    List<Customer> findByCustomerNameOrCustomerAddress(String name, String address);
}
