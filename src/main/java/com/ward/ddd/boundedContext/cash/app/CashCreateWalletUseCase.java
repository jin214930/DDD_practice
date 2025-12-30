package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.boundedContext.cash.out.CashMemberRepository;
import com.ward.ddd.boundedContext.cash.out.WalletRepository;
import com.ward.ddd.shared.cash.dto.CashMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCreateWalletUseCase {
    private final WalletRepository walletRepository;
    private final CashMemberRepository cashMemberRepository;

    public Wallet createWallet(CashMemberDto memberDto) {
        CashMember member = cashMemberRepository.getReferenceById(memberDto.id());

        Wallet wallet = Wallet.builder()
                .id(member.getId())
                .holder(member)
                .build();

        return walletRepository.save(wallet);
    }
}
