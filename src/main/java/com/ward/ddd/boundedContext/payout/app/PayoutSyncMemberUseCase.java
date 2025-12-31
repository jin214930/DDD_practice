package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import com.ward.ddd.boundedContext.payout.out.PayoutMemberRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.shared.member.dto.MemberDto;
import com.ward.ddd.shared.payout.dto.PayoutMemberDto;
import com.ward.ddd.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSyncMemberUseCase {
    private final PayoutMemberRepository payoutMemberRepository;
    private final EventPublisher eventPublisher;

    public PayoutMember syncMember(MemberDto memberDto) {
        boolean isNew = !payoutMemberRepository.existsById(memberDto.id());

        PayoutMember member = PayoutMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        payoutMemberRepository.save(member);

        if (isNew) {
            eventPublisher.publish(new PayoutMemberCreatedEvent(PayoutMemberDto.from(member)));
        }

        return member;
    }
}
