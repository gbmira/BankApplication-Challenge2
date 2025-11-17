package com.bankapplication.bankapplication.mapper.customer;

import com.bankapplication.bankapplication.dto.account.AccountResponseDTO;
import com.bankapplication.bankapplication.dto.customer.CustomerResponseDTO;
import com.bankapplication.bankapplication.model.Account;
import com.bankapplication.bankapplication.model.Customer;

public class AccountMapper {

    public static AccountResponseDTO toDTO(Account entity) {
        return new AccountResponseDTO(entity.getId(),
                entity.getAccountNumber(),
                entity.getAgencyNumber(),
                entity.getCustomer().getName(),
                entity.getAccountBalance(),
                entity.getTransferLimit());
    }
}
