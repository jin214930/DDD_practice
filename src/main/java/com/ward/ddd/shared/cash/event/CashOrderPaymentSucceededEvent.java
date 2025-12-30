package com.ward.ddd.shared.cash.event;

import com.ward.ddd.shared.market.dto.OrderDto;

public record CashOrderPaymentSucceededEvent(
        OrderDto orderDto,
        long pgPaymentAmount
) {
}
