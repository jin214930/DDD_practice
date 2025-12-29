package com.ward.ddd.boundedContext.member.eventListener;

import com.ward.ddd.boundedContext.member.entity.Member;
import com.ward.ddd.boundedContext.member.service.MemberService;
import com.ward.ddd.shared.post.event.PostCommentCreatedEvent;
import com.ward.ddd.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberService memberService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberService.findById(event.postDto().authorId());

        member.increaseActivityScore(3);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(PostCommentCreatedEvent event) {
        Member member = memberService.findById(event.postCommentDto().authorId());

        member.increaseActivityScore(1);
    }


}
