package com.ward.ddd.boundedContext.market.in;

import com.ward.ddd.boundedContext.market.app.MarketFacade;
import com.ward.ddd.shared.cash.event.CashOrderPaymentFailedEvent;
import com.ward.ddd.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.ward.ddd.shared.market.event.MarketMemberCreatedEvent;
import com.ward.ddd.shared.member.event.MemberJoinedEvent;
import com.ward.ddd.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class MarketEventListener {
    private final MarketFacade marketFacade;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        marketFacade.syncMember(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        marketFacade.syncMember(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(MarketMemberCreatedEvent event) {
        marketFacade.createCart(event.memberDto());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(CashOrderPaymentSucceededEvent event) {
        long orderId = event.orderDto().id();
        marketFacade.completeOrderPayment(orderId);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(CashOrderPaymentFailedEvent event) {
        long orderId = event.orderDto().id();
        marketFacade.cancelOrderRequestPayment(orderId);
    }
}
