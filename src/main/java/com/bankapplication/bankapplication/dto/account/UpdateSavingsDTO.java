package com.bankapplication.bankapplication.dto.account;

public record UpdateSavingsDTO(
        String agencyNumber,
        Double transferLimit,
        String accountNickname
) {
}
