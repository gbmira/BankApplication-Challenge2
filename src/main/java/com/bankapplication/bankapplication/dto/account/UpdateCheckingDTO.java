package com.bankapplication.bankapplication.dto.account;

public record UpdateCheckingDTO(
        String agencyNumber,
        Double transferLimit
) {
}
