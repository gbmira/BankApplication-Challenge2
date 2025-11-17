package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
import com.bankapplication.bankapplication.exceptions.AccountNotFoundException;
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
    public Transaction deposit(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.deposit(amount);

        accountRepository.save(account);

        return transactionRepository.save(new Transaction(TransactionType.DEPOSIT, amount, account, null));
    }

    @Transactional
    public Transaction withdraw(String accountNumber, double amount) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.withdraw(amount);

        accountRepository.save(account);

        return transactionRepository.save(new Transaction(TransactionType.WITHDRAW, amount, account, null));
    }

    @Transactional
    public Transaction transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        Account from = accountRepository.findByAccountNumber(fromAccountNumber)
                        .orElseThrow(() -> new AccountNotFoundException("Account: "+ fromAccountNumber + " not found"));

        Account to = accountRepository.findByAccountNumber(toAccountNumber)
                        .orElseThrow(() -> new AccountNotFoundException("Account: "+ toAccountNumber + " not found"));

        from.withdraw(amount);
        to.deposit(amount);

        Transaction t = new Transaction(TransactionType.TRANSFER, amount, from, to);

        from.addTransaction(t);
        to.addTransaction(t);

        accountRepository.save(from);
        accountRepository.save(to);

        return transactionRepository.save(t);
    }

    public List<Transaction> getTransactions(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<Transaction> transactions = account.getTransactions();

        return transactions;
    }

}
