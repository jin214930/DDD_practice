package com.ward.ddd.shared.payout.dto;

import com.ward.ddd.boundedContext.payout.domain.PayoutMember;

import java.time.LocalDateTime;

public record PayoutMemberDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        String username,
        String nickname,
        int activityScore
) {
    public static PayoutMemberDto from(PayoutMember member) {
        return new PayoutMemberDto(
                member.getId(),
                member.getCreatedDate(),
                member.getModifiedDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
