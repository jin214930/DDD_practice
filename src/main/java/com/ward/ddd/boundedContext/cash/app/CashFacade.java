package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.shared.cash.dto.CashMemberDto;
import com.ward.ddd.shared.market.event.MarketOrderPaymentRequestedEvent;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;
    private final CashSupport cashSupport;

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto memberDto) {
        return cashCreateWalletUseCase.createWallet(memberDto);
    }

    @Transactional(readOnly = true)
    public CashMember findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    @Transactional(readOnly = true)
    public Wallet findWalletByHolder(CashMember member) {
        return cashSupport.findWalletByHolder(member);
    }

    @Transactional
    public void handle(MarketOrderPaymentRequestedEvent event) {
        cashCompleteOrderPaymentUseCase.handle(event);

    }

    @Transactional(readOnly = true)
    public Wallet findWalletByHolderId(long holderId) {
        return cashSupport.findWalletByHolderId(holderId);
    }
}
