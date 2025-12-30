package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.boundedContext.cash.out.CashMemberRepository;
import com.ward.ddd.boundedContext.cash.out.WalletRepository;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        CashMember member = CashMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        return cashMemberRepository.save(member);
    }

    @Transactional
    public Wallet createWallet(CashMember member) {
        Wallet wallet = Wallet.builder()
                .id(member.getId())
                .holder(member)
                .build();

        return walletRepository.save(wallet);
    }

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
