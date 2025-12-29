package com.ward.ddd.domain.member.service;

import com.ward.ddd.domain.member.entity.Member;
import com.ward.ddd.domain.member.repository.MemberRepository;
import com.ward.ddd.global.exception.DomainException;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Dictionary;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public long count() {
        return memberRepository.count();
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 아이디입니다.")
        );
    }
}
