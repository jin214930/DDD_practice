package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;

    @Transactional
    public MarketMember syncMember(MemberDto memberDto) {
        return marketSyncMemberUseCase.syncMember(memberDto);
    }
}
