package com.ward.ddd.boundedContext.member.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.member.out.MemberRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.member.dto.MemberDto;
import com.ward.ddd.shared.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJoinUseCase {
    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;

    public ResponseData<Member> join(String username, String password, String nickname) {
        memberRepository.findByUsername(username).ifPresent(member -> {
            throw new DomainException(HttpStatus.CONFLICT.value(), "이미 존재하는 아이디입니다.");
        });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        memberRepository.save(member);

        eventPublisher.publish(new MemberJoinedEvent(MemberDto.from(member)));

        return ResponseData.from(201, "%d번 회원이 가입했습니다.".formatted(member.getId()), member);
    }
}
