package com.bankapplication.bankapplication.controller;

import com.bankapplication.bankapplication.dto.account.AccountDTO;
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
    public ResponseEntity<SavingsAccount> createSavingsAccount(@RequestBody AccountDTO dto) {

        SavingsAccount created = service.createSavingsAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/savings/{accountNumber}")
    public ResponseEntity<SavingsAccount> updateSavings(@PathVariable String accountNumber, @RequestBody UpdateSavingsDTO dto) {

        SavingsAccount updated = service.updateSavings(accountNumber, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PutMapping("/checking/{accountNumber}")
    public ResponseEntity<CheckingAccount> updateChecking(@PathVariable String accountNumber, @RequestBody UpdateCheckingDTO dto) {

        CheckingAccount updated = service.updateChecking(accountNumber, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @GetMapping("/checking")
    public ResponseEntity<List<CheckingAccount>> getAllCheckingAccounts() {

        List<CheckingAccount> checkingAccounts = service.getAllCheckingAccounts();

        return ResponseEntity.status(HttpStatus.OK).body(checkingAccounts);
    }

    @GetMapping("/savings")
    public ResponseEntity<List<SavingsAccount>> getAllSavingsAccounts() {

        List<SavingsAccount> savingsAccounts = service.getAllSavingsAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(savingsAccounts);
    }

    @GetMapping("/checking/{accountNumber}")
    public ResponseEntity<CheckingAccount> getCheckingAccountByAccountNumber(@PathVariable String accountNumber) {

        CheckingAccount checkingAccount = service.getCheckingAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(checkingAccount);
    }

    @GetMapping("/savings/{accountNumber}")
    public ResponseEntity<SavingsAccount> getSavingsAccountByAccountNumber(@PathVariable String accountNumber) {

        SavingsAccount savingsAccount = service.getSavingsAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(savingsAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {

        List<Account> accounts = service.getAllAcounts();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {

        service.deleteAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
