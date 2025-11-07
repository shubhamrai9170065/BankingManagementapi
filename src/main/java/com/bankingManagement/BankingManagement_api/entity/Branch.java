package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Table(name = "T_Branch")
@Entity
@Getter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "branch_id_sequence")
    @SequenceGenerator(name = "branch_id_sequence", sequenceName = "branch_id_sequence", allocationSize = 1)
    @Column(name = "Branch_ID")
    private int branchID;

    @Column(name = "Branch_Name")
    private String branchName;

    @Column(name = "Branch_Address")
    private String branchAddress;

    //one to one mapping with joining table which create one extra table in db not best approach;

    // one to one mapping by using foreign key best approach;
//    @OneToOne
//    @JoinColumn(name = "Bank_code" )
//    private Bank bank;

    // one to many mapping by using foreign key best approach;
    @ManyToOne
    @JoinColumn(name = "Bank_Code")
    private Bank bank;

    @OneToMany(mappedBy = "branch")
    private Set<Loan> loan;

    @OneToMany(mappedBy = "branch")
    private Set<Account> account;

}
