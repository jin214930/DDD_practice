package com.ward.ddd.boundedContext.member.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberJoinUseCase memberJoinUseCase;
    private final MemberGetRandomSecureTipUseCase memberGetRandomSecureTipUseCase;
    private final MemberSupport memberSupport;

    @Transactional(readOnly = true)
    public long count() {
        return memberSupport.count();
    }

    @Transactional
    public ResponseData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberSupport.findById(id);
    }

    public String getRandomSecureTip() {
        return memberGetRandomSecureTipUseCase.getRandomSecureTip();
    }
}
