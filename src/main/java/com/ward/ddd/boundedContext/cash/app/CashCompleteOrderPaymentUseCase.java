package com.ward.ddd.boundedContext.cash.app;

import com.ward.ddd.boundedContext.cash.domain.CashLog;
import com.ward.ddd.boundedContext.cash.domain.Wallet;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.shared.cash.event.CashOrderPaymentFailedEvent;
import com.ward.ddd.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.ward.ddd.shared.market.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCompleteOrderPaymentUseCase {
    private final CashSupport cashSupport;
    private final EventPublisher eventPublisher;

    public void completeOrderPayment(OrderDto orderDto, long pgPaymentAmount) {
        Wallet buyerWallet = cashSupport.findWalletByHolderId(orderDto.buyerId());
        Wallet holdingWallet = cashSupport.findHoldingWallet();

        if (pgPaymentAmount > 0) {
            buyerWallet.credit(
                    pgPaymentAmount,
                    CashLog.EventType.충전__PG결제_토스페이먼츠,
                    orderDto.getModelTypeCode(),
                    orderDto.id()
            );
        }

        boolean canPay = buyerWallet.getBalance() >= orderDto.salePrice();

        if (canPay) {
            buyerWallet.debit(
                    orderDto.salePrice(),
                    CashLog.EventType.사용__주문결제,
                    orderDto.getModelTypeCode(),
                    orderDto.id()
            );

            holdingWallet.credit(
                    orderDto.salePrice(),
                    CashLog.EventType.임시보관__주문결제,
                    orderDto.getModelTypeCode(),
                    orderDto.id()
            );

            eventPublisher.publish(
                    new CashOrderPaymentSucceededEvent(orderDto, pgPaymentAmount)
            );
        } else {
            eventPublisher.publish(
                    new CashOrderPaymentFailedEvent(
                            HttpStatus.BAD_REQUEST.value(),
                            "충전은 완료했지만 %d번 주문을 결제하기에는 예치금이 부족합니다.".formatted(orderDto.id()),
                            orderDto,
                            pgPaymentAmount,
                            pgPaymentAmount - buyerWallet.getBalance()
                    )
            );
        }
    }
}
