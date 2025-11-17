package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
import com.bankapplication.bankapplication.dto.account.UpdateCheckingDTO;
import com.bankapplication.bankapplication.dto.account.UpdateSavingsDTO;
import com.bankapplication.bankapplication.exceptions.AccountNotFoundException;
import com.bankapplication.bankapplication.exceptions.CustomerNotFoundException;
import com.bankapplication.bankapplication.model.*;
import com.bankapplication.bankapplication.repository.AccountRepository;
import com.bankapplication.bankapplication.repository.CheckingAccountRepository;
import com.bankapplication.bankapplication.repository.CustomerRepository;
import com.bankapplication.bankapplication.repository.SavingsAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private AccountRepository repository;
    private CheckingAccountRepository checkingAccountRepository;
    private SavingsAccountRepository savingsAccountRepository;
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository repository, CheckingAccountRepository checkingAccountRepository, SavingsAccountRepository savingsAccountRepository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.checkingAccountRepository = checkingAccountRepository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.customerRepository = customerRepository;
    }

    public CheckingAccount createCheckingAccount(AccountDTO dto) {

        Customer customer = customerRepository.findByCpf(dto.customerCPF())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        CheckingAccount account = new CheckingAccount();

        account.setAccountNumber(dto.accountNumber());
        account.setAgencyNumber(dto.agencyNumber());
        account.setTransferLimit(dto.transferLimit());
        account.setAccountBalance(0.0);
        account.setCustomer(customer);

        return repository.save(account);
    }

    public SavingsAccount createSavingsAccount(AccountDTO dto) {

        Customer customer = customerRepository.findByCpf(dto.customerCPF())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        SavingsAccount account = new SavingsAccount();

        account.setAccountNumber(dto.accountNumber());
        account.setAgencyNumber(dto.agencyNumber());
        account.setTransferLimit(dto.transferLimit());
        account.setAccountBalance(0.0);
        account.setCustomer(customer);
        account.setAccountNickname(dto.accountNickname());

        return repository.save(account);
    }

    public SavingsAccount updateSavings(String accountNumber, UpdateSavingsDTO dto) {

        SavingsAccount acc = savingsAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountNumber));

        if (dto.accountNickname() != null) {
            acc.setAccountNickname(dto.accountNickname());
        }
        if (dto.transferLimit() != null) {
            acc.setTransferLimit(dto.transferLimit());
        }
        if (dto.agencyNumber() != null) {
            acc.setAgencyNumber(dto.agencyNumber());
        }

        repository.save(acc);

        return acc;
    }

    public CheckingAccount updateChecking(String accountNumber, UpdateCheckingDTO dto) {
        CheckingAccount acc = checkingAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        acc.setTransferLimit(dto.transferLimit());
        acc.setAgencyNumber(dto.agencyNumber());

        repository.save(acc);

        return acc;
    }

    public List<CheckingAccount> getAllCheckingAccounts() {

        List<CheckingAccount> checkingAccounts = checkingAccountRepository.findAll();

        return checkingAccounts;
    }

    public List<SavingsAccount> getAllSavingsAccounts() {

        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAll();

        return savingsAccounts;
    }

    public CheckingAccount getCheckingAccountByAccountNumber(String accountNumber) {

        CheckingAccount account = checkingAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return account;
    }

    public SavingsAccount getSavingsAccountByAccountNumber(String accountNumber) {

        SavingsAccount account = savingsAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return account;
    }

    public void deleteAccount(String accountNumber) {

        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        repository.delete(account);
    }

    public List<Account> getAllAcounts() {

        List<Account> accounts = repository.findAll();
        return accounts;
    }
}
