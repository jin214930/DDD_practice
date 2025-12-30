package com.ward.ddd.boundedContext.member.app;

import com.ward.ddd.boundedContext.member.domin.MemberPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberGetRandomSecureTipUseCase {
    private final MemberPolicy memberPolicy;

    public String getRandomSecureTip() {
        return "비밀번호의 유효기간은 %d일입니다."
                .formatted(memberPolicy.getNeedToChangePasswordDays());
    }
}
