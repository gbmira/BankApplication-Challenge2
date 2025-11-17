package com.bankapplication.bankapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;
    private String agencyNumber;

    @ManyToOne
    @JsonIgnoreProperties("bankAccounts")
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private Double accountBalance;
    private Double transferLimit;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"source", "destination"})
    private List<Transaction> transactions = new ArrayList<>();

    public Account(){}

    public Account(String accountNumber, String agencyNumber, Customer customer, Double accountBalance,
                   Double transferLimit) {
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.customer = customer;
        this.accountBalance = accountBalance;
        this.transferLimit = transferLimit;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getTransferLimit() {
        return transferLimit;
    }

    public void setTransferLimit(Double transferLimit) {
        this.transferLimit = transferLimit;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" +
                "Agency Number: " + agencyNumber + "\n" +
                "Customer: " + customer + "\n" +
                "Account Balance: " + accountBalance + "\n" +
                "Transfer Limit: " + transferLimit + "\n";
    }

    public void deposit(Double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit!");
            return;
        }

        this.accountBalance += amount;
        addTransaction(new Transaction(TransactionType.DEPOSIT, amount, this, null));
    }

    public abstract void withdraw(Double amount);

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
