package com.bankapplication.bankapplication.dto.account;

import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.Customer;
import com.bankapplication.bankapplication.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private String accountNumber;
    private String agencyNumber;
    private String customerName;
    private Double accountBalance;
    private Double transferLimit;

    public AccountResponseDTO(Long id, String accountNumber, String agencyNumber, String customerName, Double accountBalance, Double transferLimit) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.transferLimit = transferLimit;
        this.customerName = customerName;
        this.accountBalance = accountBalance;
    }

}
