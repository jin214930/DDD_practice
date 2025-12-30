package com.ward.ddd.shared.member.dto;

import com.ward.ddd.boundedContext.member.domin.Member;

import java.time.LocalDateTime;

public record MemberDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        String username,
        String nickname,
        int activityScore
) {
    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getCreatedDate(),
                member.getModifiedDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
