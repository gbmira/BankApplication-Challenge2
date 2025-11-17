package com.bankapplication.bankapplication.dto.transaction;

import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {

    private Long id;
    private LocalDateTime timestamp;
    private TransactionType type;
    private Double amount;
    private String sourceName;
    private String sourceAccountNumber;
    private Double sourceAccountBalance;
    private String destinationName;
    private String destinationAccountNumber;
    private Double destinationAccountBalance;

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(Long id, LocalDateTime timestamp, TransactionType type, Double amount, String sourceName, String sourceAccountNumber, Double sourceAccountBalance, String destinationName, String destinationAccountNumber, Double destinationAccountBalance) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.sourceName = sourceName;
        this.sourceAccountNumber = sourceAccountNumber;
        this.sourceAccountBalance = sourceAccountBalance;
        this.destinationName = destinationName;
        this.destinationAccountNumber = destinationAccountNumber;
        this.destinationAccountBalance = destinationAccountBalance;
    }

    public TransactionResponseDTO(Long id, LocalDateTime timestamp, TransactionType type, Double amount, String sourceName, String sourceAccountNumber, Double sourceAccountBalance) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.sourceName = sourceName;
        this.sourceAccountNumber = sourceAccountNumber;
        this.sourceAccountBalance = sourceAccountBalance;
    }
}
