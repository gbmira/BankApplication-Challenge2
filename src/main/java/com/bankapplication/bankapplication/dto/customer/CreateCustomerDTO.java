package com.bankapplication.bankapplication.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record CreateCustomerDTO(
        String name,

        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        String phoneNumber,

        @Email(message = "E-mail inválido")
        String email
) {
}
