package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import com.ward.ddd.boundedContext.cash.out.CashMemberRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.shared.cash.dto.CashMemberDto;
import com.ward.ddd.shared.cash.event.CashMemberCreatedEvent;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final EventPublisher eventPublisher;

    public CashMember syncMember(MemberDto memberDto) {
        boolean isNew = !cashMemberRepository.existsById(memberDto.id());

        CashMember member = CashMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        cashMemberRepository.save(member);

        if (isNew) {
            eventPublisher.publish(new CashMemberCreatedEvent(CashMemberDto.from(member)));
        }

        return member;
    }
}
