package com.ward.ddd.shared.cash.dto;

import com.ward.ddd.boundedContext.cash.domain.CashMember;

import java.time.LocalDateTime;

public record CashMemberDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        String username,
        String nickname,
        int activeScore
) {
    public static CashMemberDto from(CashMember member) {
        return new CashMemberDto(
                member.getId(),
                member.getCreatedDate(),
                member.getModifiedDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
