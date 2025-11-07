package com.bankingManagement.BankingManagement_api.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "T_Loan")
@Getter
public class Loan {
    private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_sequence")
    @SequenceGenerator(name = "loan_id_sequence", sequenceName = "loan_id_sequence", allocationSize = 1)
    @Column(name = "Loan_ID")
    private int loanId;
    @Column(name = "Loan_Type")
    private String loanType;
    @Column(name = "Loan_Amount")
    private double loanAmount;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "Cust_ID")
    private Customer customers;
}
