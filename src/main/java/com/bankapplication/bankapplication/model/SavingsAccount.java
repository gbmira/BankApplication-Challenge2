package com.bankapplication.bankapplication.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SavingsAccount extends Account{

    private String accountNickname;

    public SavingsAccount() {}

    public SavingsAccount(String accountNumber, String agencyNumber, Customer customer, Double accountBalance,
                          Double transferLimit, String accountNickname) {
        super(accountNumber, agencyNumber, customer, accountBalance, transferLimit);
        this.accountNickname = accountNickname;
    }

    public SavingsAccount(String accountNumber, String agencyNumber, Double transferLimit, String accountNickname) {
        super(accountNumber, agencyNumber, null, 0.0, transferLimit);
        this.accountNickname = accountNickname;
    }

    public String getAccountNickname() {
        return accountNickname;
    }

    public void setAccountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
    }

    @Override
    public void deposit(Double amount) {
        super.deposit(amount);
        double interest = amount * 0.005;
        setAccountBalance(getAccountBalance() + interest);
    }

    public void withdraw(Double amount) {
        if(amount < 0) throw new IllegalArgumentException("invalid withdraw");

        if (amount < getAccountBalance() && amount < getTransferLimit()) {
            setAccountBalance(getAccountBalance() - amount);
            addTransaction(new Transaction(TransactionType.WITHDRAW, amount, this, null));
        } else {
            System.out.println("Insufficient funds or Limit exceeded, try again with another value.");
        }
    }
}
