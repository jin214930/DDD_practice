package com.ward.ddd.domain.member.service;

import com.ward.ddd.domain.member.entity.Member;
import com.ward.ddd.domain.member.repository.MemberRepository;
import com.ward.ddd.global.exception.DomainException;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        memberRepository.findByUsername(username).ifPresent(member -> {
            throw new DomainException(HttpStatus.CONFLICT.value(), "이미 존재하는 아이디입니다.");
        });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }
}
