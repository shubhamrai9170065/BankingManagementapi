// Customer.java
package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "T_Customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custSeq")
    @SequenceGenerator(name = "custSeq", sequenceName = "cust_seq", allocationSize = 1)
    @Column(name = "cust_id")
    private Integer customerId;

    @Column(name = "cust_name")
    private String customerName;

    @Column(name = "cust_phone")
    private Long customerPhone;

    @Column(name = "cust_address")
    private String customerAddress;

    // Correct mapping (IMPORTANT)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Loan> loans;
}
