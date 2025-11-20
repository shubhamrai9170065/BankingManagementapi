// Branch.java
package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "T_Branch")
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branchSeq")
    @SequenceGenerator(name = "branchSeq", sequenceName = "branch_seq", allocationSize = 1)
    @Column(name = "branch_id")
    private Long branchID;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "branch_address")
    private String branchAddress;

    @ManyToOne
    @JoinColumn(name = "bank_code")
    private Bank bank;

    @OneToMany(mappedBy = "branch")
    private Set<Account> accounts;

    @OneToMany(mappedBy = "branch")
    private Set<Loan> loans;
}
