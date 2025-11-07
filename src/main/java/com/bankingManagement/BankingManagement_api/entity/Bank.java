package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Table(name = "T_Bank")
@Entity
@Getter
@Setter
public class Bank implements Serializable {
    private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_id_sequence")
    @SequenceGenerator(name = "bank_id_sequence",sequenceName = "bank_code_sequence",allocationSize = 1)
    @Column(name = "Bank_Code")
    private int bankCode;

    @Column(name = "Bank_Name")
    private String bankName;

    @Column(name = "Bank_Address")
    private String bankAddress;

//    @OneToOne(mappedBy = "bank")  // this is same when you connect with foreign key or joining table
//private Set<Branch> branch;

    @OneToMany(mappedBy = "bank")  // this is same when you connect with foreign key or joining table
    private Set<Branch> branch;

    @OneToMany(mappedBy = "bank")
    private Set<Account> account;
}
