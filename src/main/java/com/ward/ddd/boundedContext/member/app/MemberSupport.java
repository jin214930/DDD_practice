package com.ward.ddd.boundedContext.member.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.member.out.MemberRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSupport {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.")
        );
    }
}
