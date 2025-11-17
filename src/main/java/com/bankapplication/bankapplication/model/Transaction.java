package com.bankapplication.bankapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private Double amount;

    @ManyToOne
    @JsonIgnoreProperties({"transactions"})
    private Account source;

    @ManyToOne
    @JsonIgnoreProperties({"transactions"})
    private Account destination;

    public Transaction(TransactionType type, Double amount, Account source, Account destination) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.source = source;
        this.destination = destination;
    }


    public Transaction() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "\n--- Transaction ---" +
                "\nTimestamp: " + timestamp +
                "\nType: " + type +
                "\nAmount: " + amount +
                "\nSource Account: " +
                (source != null ? source.getAccountNumber() : "N/A") +
                "\nDestination Account: " +
                (destination != null ? destination.getAccountNumber() : "N/A") +
                "\n-------------------";
    }
}
