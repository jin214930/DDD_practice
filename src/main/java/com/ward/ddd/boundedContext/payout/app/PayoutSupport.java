package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import com.ward.ddd.boundedContext.payout.out.PayoutMemberRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSupport {
    private final PayoutMemberRepository payoutMemberRepository;

    public PayoutMember findSystemMember() {
        return payoutMemberRepository.findByUsername("system").orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "시스템 회원을 찾을 수 없습니다.")
        );
    }

    public PayoutMember findMemberById(Long id) {
        return payoutMemberRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "회원을 찾을 수 없습니다.")
        );
    }
}
