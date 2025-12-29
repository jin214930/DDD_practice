package com.ward.ddd.boundedContext.member.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.member.out.MemberRepository;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJoinUseCase {
    private final MemberRepository memberRepository;

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

        return ResponseData.from(201, "%d번 회원이 가입했습니다.".formatted(member.getId()), member);
    }
}
