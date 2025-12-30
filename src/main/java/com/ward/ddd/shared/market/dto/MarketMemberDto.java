package com.ward.ddd.shared.market.dto;

import com.ward.ddd.boundedContext.market.domain.MarketMember;

import java.time.LocalDateTime;

public record MarketMemberDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        String username,
        String nickname,
        int activityScore
) {
    public static MarketMemberDto from(MarketMember member) {
        return new MarketMemberDto(
                member.getId(),
                member.getCreatedDate(),
                member.getModifiedDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
