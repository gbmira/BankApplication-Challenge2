package com.bankapplication.bankapplication.dto.account;

public record AccountDTO(String accountNumber, String agencyNumber, Double transferLimit, String customerCPF, String accountNickname) {
}
