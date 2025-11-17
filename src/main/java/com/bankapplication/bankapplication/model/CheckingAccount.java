package com.bankapplication.bankapplication.model;

import com.bankapplication.bankapplication.exceptions.InvalidTransactionException;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CheckingAccount extends Account {

    public CheckingAccount() {
    }

    public CheckingAccount(String accountNumber, String agencyNumber, Customer consumer, Double accountBalance,
                           Double transferLimit) {
        super(accountNumber, agencyNumber, consumer, accountBalance, transferLimit);
    }

    public CheckingAccount(String accountNumber, String agencyNumber, Double transferLimit) {
        super(accountNumber, agencyNumber, null, 0.0, transferLimit);
    }

    @Override
    public void withdraw(Double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("invalid withdraw");

        int hour = LocalDateTime.now().getHour();

        double availableLimit = getTransferLimit();

        if (hour >= 22) {
            availableLimit = 500;
        }

        double fee = 2.0;
        if (amount + fee < getAccountBalance() && amount < availableLimit) {
            setAccountBalance(getAccountBalance() - amount - fee);
            addTransaction(new Transaction(TransactionType.WITHDRAW, amount, this, null));
        } else {
            throw new InvalidTransactionException("Insufficient funds or Limit exceeded, try again with another value.");
        }

    }
}
