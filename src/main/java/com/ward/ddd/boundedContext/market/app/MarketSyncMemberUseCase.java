package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.out.MarketMemberRepository;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketSyncMemberUseCase {
    private final MarketMemberRepository marketMemberRepository;

    public MarketMember syncMember(MemberDto memberDto) {
        MarketMember member = MarketMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        return marketMemberRepository.save(member);
    }
}
