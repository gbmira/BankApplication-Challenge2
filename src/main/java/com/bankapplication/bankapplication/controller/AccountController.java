package com.bankapplication.bankapplication.controller;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
import com.bankapplication.bankapplication.dto.account.AccountResponseDTO;
import com.bankapplication.bankapplication.dto.account.UpdateCheckingDTO;
import com.bankapplication.bankapplication.dto.account.UpdateSavingsDTO;
import com.bankapplication.bankapplication.exceptions.AccountNotFoundException;
import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.CheckingAccount;
import com.bankapplication.bankapplication.model.SavingsAccount;
import com.bankapplication.bankapplication.service.AccountService;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/checking")
    public ResponseEntity<Account> createCheckingAccount(@RequestBody AccountDTO dto) {

        CheckingAccount created = service.createCheckingAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //metodos iguais, porem se alterar algum atributo na classe, apenas alterariamos o DTO.
    @PostMapping("/savings")
    public ResponseEntity<AccountResponseDTO> createSavingsAccount(@RequestBody AccountDTO dto) {

        AccountResponseDTO created = service.createSavingsAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/savings/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> updateSavings(@PathVariable String accountNumber, @RequestBody UpdateSavingsDTO dto) {

        AccountResponseDTO updated = service.updateSavings(accountNumber, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PutMapping("/checking/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> updateChecking(@PathVariable String accountNumber, @RequestBody UpdateCheckingDTO dto) {

        AccountResponseDTO updated = service.updateChecking(accountNumber, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @GetMapping("/checking")
    public ResponseEntity<List<AccountResponseDTO>> getAllCheckingAccounts() {

        List<AccountResponseDTO> checkingAccounts = service.getAllCheckingAccounts();

        return ResponseEntity.status(HttpStatus.OK).body(checkingAccounts);
    }

    @GetMapping("/savings")
    public ResponseEntity<List<AccountResponseDTO>> getAllSavingsAccounts() {

        List<AccountResponseDTO> savingsAccounts = service.getAllSavingsAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(savingsAccounts);
    }

    @GetMapping("/checking/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> getCheckingAccountByAccountNumber(@PathVariable String accountNumber) {

        AccountResponseDTO checkingAccount = service.getCheckingAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(checkingAccount);
    }

    @GetMapping("/savings/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> getSavingsAccountByAccountNumber(@PathVariable String accountNumber) {

        AccountResponseDTO savingsAccount = service.getSavingsAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(savingsAccount);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {

        List<AccountResponseDTO> accounts = service.getAllAcounts();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {

        service.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
