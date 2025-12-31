package com.ward.ddd.boundedContext.payout.in;

import com.ward.ddd.boundedContext.payout.app.PayoutFacade;
import com.ward.ddd.shared.market.event.MarketOrderPaymentCompletedEvent;
import com.ward.ddd.shared.member.event.MemberJoinedEvent;
import com.ward.ddd.shared.member.event.MemberModifiedEvent;
import com.ward.ddd.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PayoutEventListener {
    private final PayoutFacade payoutFacade;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        payoutFacade.syncMember(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        payoutFacade.syncMember(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(PayoutMemberCreatedEvent event) {
        payoutFacade.createPayout(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MarketOrderPaymentCompletedEvent event) {
        payoutFacade.addPayoutCandidateItems(event.orderDto());
    }
}
