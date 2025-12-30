package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.out.CashMemberRepository;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;

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
}
