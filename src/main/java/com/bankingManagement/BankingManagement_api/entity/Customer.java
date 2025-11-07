package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "T_Customer")
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_sequence")
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence", allocationSize = 1)
    @Column(name = "Cust_ID")
    private int customerId;

    @Column(name = "Cust_Name")
    private String customerName;

    @Column(name = "Cust_Phone")
    private long customerPhone;
    @Column(name = "Cust_Address")
    private String customerAddress;


    @OneToMany(mappedBy = "customers")
    private Set<Loan>loans;


    @OneToMany(mappedBy = "customers")
    private Set<Account> accounts;
}
