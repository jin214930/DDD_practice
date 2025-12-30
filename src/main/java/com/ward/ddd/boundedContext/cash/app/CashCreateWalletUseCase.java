package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.boundedContext.cash.out.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final WalletRepository walletRepository;
    
    public Wallet createWallet(CashMember member) {
        Wallet wallet = Wallet.builder()
                .id(member.getId())
                .holder(member)
                .build();

        return walletRepository.save(wallet);
    }
}
