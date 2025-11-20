// Bank.java
package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "T_Bank")
@Getter
@Setter
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq", allocationSize = 1)
    @Column(name = "bank_code")
    private Long bankCode;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_address")
    private String bankAddress;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Branch> branches;

}
