package com.ward.ddd.shared.cash.dto;

import com.ward.ddd.boundedContext.cash.domain.Wallet;

import java.time.LocalDateTime;

public record WalletDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long holderId,
        String holderName,
        long balance
) {
    public static WalletDto from(Wallet wallet) {
        return new WalletDto(
                wallet.getId(),
                wallet.getCreatedDate(),
                wallet.getModifiedDate(),
                wallet.getHolder().getId(),
                wallet.getHolder().getNickname(),
                wallet.getBalance()
        );
    }
}
