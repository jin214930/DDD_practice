package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.out.MarketMemberRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.shared.market.dto.MarketMemberDto;
import com.ward.ddd.shared.market.event.MarketMemberCreatedEvent;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketSyncMemberUseCase {
    private final MarketMemberRepository marketMemberRepository;
    private final EventPublisher eventPublisher;

    public MarketMember syncMember(MemberDto memberDto) {
        boolean isNew = !marketMemberRepository.existsById(memberDto.id());

        MarketMember member = MarketMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        marketMemberRepository.save(member);

        if (isNew) {
            eventPublisher.publish(new MarketMemberCreatedEvent(MarketMemberDto.from(member)));
        }

        return member;
    }
}
