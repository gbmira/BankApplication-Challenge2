package com.bankapplication.bankapplication.dto.transaction;

public record TransferDTO(String fromNumberAccount, String toNumberAccount, double amount) {
}
