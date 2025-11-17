package com.bankapplication.bankapplication.mapper.customer;

import com.bankapplication.bankapplication.dto.customer.CustomerResponseDTO;
import com.bankapplication.bankapplication.model.Customer;

public class CustomerMapper {

    public static CustomerResponseDTO toDTO(Customer entity) {
        return new CustomerResponseDTO(entity.getId(),
                entity.getName(),
                entity.getCpf(), entity.getPhoneNumber(), entity.getEmail());
    }
}
