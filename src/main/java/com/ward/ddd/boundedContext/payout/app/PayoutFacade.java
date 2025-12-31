package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.boundedContext.payout.domain.Payout;
import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import com.ward.ddd.shared.market.dto.OrderDto;
import com.ward.ddd.shared.member.dto.MemberDto;
import com.ward.ddd.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayoutFacade {
    private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;
    private final PayoutCreatePayoutUseCase payoutCreatePayoutUseCase;
    private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;

    @Transactional
    public PayoutMember syncMember(MemberDto memberDto) {
        return payoutSyncMemberUseCase.syncMember(memberDto);
    }

    public Payout createPayout(PayoutMemberDto memberDto) {
        return payoutCreatePayoutUseCase.createPayout(memberDto);
    }

    public void addPayoutCandidateItems(OrderDto orderDto) {
        payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(orderDto);
    }
}
