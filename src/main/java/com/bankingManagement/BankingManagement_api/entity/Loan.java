package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_Loan")
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loanSeq")
    @SequenceGenerator(name = "loanSeq", sequenceName = "loan_seq", allocationSize = 1)
    @Column(name = "loan_id")
    private Long loanID;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
}

