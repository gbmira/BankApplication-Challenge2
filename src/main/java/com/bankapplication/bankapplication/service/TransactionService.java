package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
import com.bankapplication.bankapplication.dto.transaction.TransactionResponseDTO;
import com.bankapplication.bankapplication.exceptions.AccountNotFoundException;
import com.bankapplication.bankapplication.exceptions.InsufficientBalanceException;
import com.bankapplication.bankapplication.exceptions.InvalidTransactionException;
import com.bankapplication.bankapplication.mapper.customer.AccountMapper;
import com.bankapplication.bankapplication.mapper.customer.TransactionMapper;
import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.Transaction;
import com.bankapplication.bankapplication.model.TransactionType;
import com.bankapplication.bankapplication.repository.AccountRepository;
import com.bankapplication.bankapplication.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;


    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionResponseDTO deposit(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.deposit(amount);

        accountRepository.save(account);

        return TransactionMapper.toDTO(transactionRepository.save(new Transaction(TransactionType.DEPOSIT, amount, account, null)));
    }

    @Transactional
    public TransactionResponseDTO withdraw(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.withdraw(amount);

        accountRepository.save(account);

        return TransactionMapper.toDTO(transactionRepository.save(new Transaction(TransactionType.WITHDRAW, amount, account, null)));
    }

    @Transactional
    public TransactionResponseDTO transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new InvalidTransactionException("Cannot transfer to the same account");
        }

        Account from = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account: " + fromAccountNumber + " not found"));

        Account to = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account: " + toAccountNumber + " not found"));

        if (amount <= 0) {
            throw new InvalidTransactionException("Amount must be greater than 0");
        }

        if (from.getAccountBalance() < amount) {
            throw new InsufficientBalanceException("Amount not enough");
        }

        from.withdraw(amount);
        to.deposit(amount);

        Transaction t = new Transaction(TransactionType.TRANSFER, amount, from, to);

        transactionRepository.save(t);

        return TransactionMapper.toDTO(t);
    }

    public List<TransactionResponseDTO> getTransactions(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<TransactionResponseDTO> transactions = account.getTransactions().stream().map(TransactionMapper::toDTO).toList();

        return transactions;
    }

}
