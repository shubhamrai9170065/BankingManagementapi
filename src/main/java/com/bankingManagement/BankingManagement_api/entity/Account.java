// Account.java
package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "T_Account")
@Getter
@Setter

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
    @SequenceGenerator(name = "accountSeq", sequenceName = "account_seq", allocationSize = 1)
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_balance")
    private Double accountBalance;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
}
