package com.bankapplication.bankapplication.mapper.customer;

import com.bankapplication.bankapplication.dto.customer.CustomerResponseDTO;
import com.bankapplication.bankapplication.dto.transaction.TransactionResponseDTO;
import com.bankapplication.bankapplication.model.Customer;
import com.bankapplication.bankapplication.model.Transaction;

public class TransactionMapper {

    public static TransactionResponseDTO toDTO(Transaction entity) {

        if (entity.getDestination() != null) {
            return new TransactionResponseDTO(entity.getId(),
                    entity.getTimestamp(),
                    entity.getType(),
                    entity.getAmount(),
                    entity.getSource().getCustomer().getName(),
                    entity.getSource().getAccountNumber(),
                    entity.getSource().getAccountBalance(),
                    entity.getDestination().getCustomer().getName(),
                    entity.getDestination().getAccountNumber(),
                    entity.getDestination().getAccountBalance());
        }
        return new TransactionResponseDTO(entity.getId(),
                entity.getTimestamp(),
                entity.getType(),
                entity.getAmount(),
                entity.getSource().getCustomer().getName(),
                entity.getSource().getAccountNumber(),
                entity.getSource().getAccountBalance());
    }
}
