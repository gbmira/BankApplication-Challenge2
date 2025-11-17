package com.bankapplication.bankapplication.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private String phoneNumber;
    private String email;
}
