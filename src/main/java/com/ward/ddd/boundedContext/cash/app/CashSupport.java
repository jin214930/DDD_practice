package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.boundedContext.cash.out.CashMemberRepository;
import com.ward.ddd.boundedContext.cash.out.WalletRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSupport {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    public CashMember findMemberByUsername(String username) {
        return cashMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 username입니다.")
        );
    }

    public Wallet findWalletByHolder(CashMember member) {
        return walletRepository.findByHolder(member).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.")
        );
    }
}
