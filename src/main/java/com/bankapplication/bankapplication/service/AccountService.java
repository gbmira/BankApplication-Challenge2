package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
import com.bankapplication.bankapplication.dto.account.AccountResponseDTO;
import com.bankapplication.bankapplication.dto.account.UpdateCheckingDTO;
import com.bankapplication.bankapplication.dto.account.UpdateSavingsDTO;
import com.bankapplication.bankapplication.exceptions.AccountNotFoundException;
import com.bankapplication.bankapplication.exceptions.CustomerNotFoundException;
import com.bankapplication.bankapplication.mapper.customer.AccountMapper;
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

    public AccountResponseDTO createSavingsAccount(AccountDTO dto) {

        Customer customer = customerRepository.findByCpf(dto.customerCPF())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        SavingsAccount account = new SavingsAccount();

        account.setAccountNumber(dto.accountNumber());
        account.setAgencyNumber(dto.agencyNumber());
        account.setTransferLimit(dto.transferLimit());
        account.setAccountBalance(0.0);
        account.setCustomer(customer);
        account.setAccountNickname(dto.accountNickname());

        repository.save(account);

        return AccountMapper.toDTO(account);
    }

    public AccountResponseDTO updateSavings(String accountNumber, UpdateSavingsDTO dto) {

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

        return AccountMapper.toDTO(acc);
    }

    public AccountResponseDTO updateChecking(String accountNumber, UpdateCheckingDTO dto) {
        CheckingAccount acc = checkingAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        acc.setTransferLimit(dto.transferLimit());
        acc.setAgencyNumber(dto.agencyNumber());

        repository.save(acc);

        return AccountMapper.toDTO(acc);
    }

    public List<AccountResponseDTO> getAllCheckingAccounts() {

        List<AccountResponseDTO> checkingAccounts = checkingAccountRepository.findAll()
                .stream()
                .map(AccountMapper::toDTO)
                .toList();

        return checkingAccounts;
    }

    public List<AccountResponseDTO> getAllSavingsAccounts() {

        List<AccountResponseDTO> savingsAccounts = savingsAccountRepository.findAll()
                .stream()
                .map(AccountMapper::toDTO)
                .toList();

        return savingsAccounts;
    }

    public AccountResponseDTO getCheckingAccountByAccountNumber(String accountNumber) {

        CheckingAccount account = checkingAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return AccountMapper.toDTO(account);
    }

    public AccountResponseDTO getSavingsAccountByAccountNumber(String accountNumber) {

        SavingsAccount account = savingsAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return AccountMapper.toDTO(account);
    }

    public void deleteAccount(String accountNumber) {

        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        repository.delete(account);
    }

    public List<AccountResponseDTO> getAllAcounts() {

        List<AccountResponseDTO> accounts = repository.findAll()
                .stream()
                .map(AccountMapper::toDTO)
                .toList();

        return accounts;
    }
}
