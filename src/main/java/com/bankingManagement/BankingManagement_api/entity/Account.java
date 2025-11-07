package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "T_Account")
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_number_sequence")
    @SequenceGenerator(name = "account_number_sequence", sequenceName = "account_id_sequence", allocationSize = 1)
    @Column(name = "Account_Number")
    private int accountNumber;

    @Column(name = "Account_Type")
    private String accountType;

    @Column(name = "Account_Balance")
    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;


    @ManyToOne
    @JoinColumn(name = "Cust_ID")
    private Customer customers;

    @ManyToOne
    @JoinColumn(name = "Bank_ID")
    private Bank bank;

}
