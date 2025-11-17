package com.bankapplication.bankapplication.controller;

import com.bankapplication.bankapplication.dto.transaction.DepositDTO;
import com.bankapplication.bankapplication.dto.transaction.TransactionResponseDTO;
import com.bankapplication.bankapplication.dto.transaction.TransferDTO;
import com.bankapplication.bankapplication.dto.transaction.WithdrawDTO;
import com.bankapplication.bankapplication.model.Transaction;
import com.bankapplication.bankapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(@PathVariable String accountNumber) {

        List<TransactionResponseDTO> transactions = transactionService.getTransactions(accountNumber);

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponseDTO> deposit(@RequestBody DepositDTO depositDTO) {

        TransactionResponseDTO transaction = transactionService.deposit(depositDTO.accountNumber(), depositDTO.amount());

        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponseDTO> withdraw(@RequestBody WithdrawDTO withdrawDTO) {

        TransactionResponseDTO transaction = transactionService.withdraw(withdrawDTO.accountNumber(), withdrawDTO.amount());

        return ResponseEntity.ok(transaction);
    }


    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(@RequestBody TransferDTO transferDTO) {

        TransactionResponseDTO transaction = transactionService.transfer(transferDTO.fromNumberAccount(), transferDTO.toNumberAccount(), transferDTO.amount());

        return ResponseEntity.ok(transaction);
    }

}
