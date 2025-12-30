package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.Order;
import com.ward.ddd.boundedContext.market.out.OrderRepository;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.shared.cash.event.CashOrderPaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCancelOrderRequestPaymentUseCase {
    private final OrderRepository orderRepository;

    public void handle(CashOrderPaymentFailedEvent event) {
        Order order = orderRepository.findById(event.orderDto().id()).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 주문입니다.")
        );

        order.cancelRequestPayment();
    }
}
