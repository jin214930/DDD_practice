package com.ward.ddd.shared.cash.event;

import com.ward.ddd.shared.market.dto.OrderDto;

public record CashOrderPaymentFailedEvent(
        int resultCode,
        String message,
        OrderDto orderDto,
        long pgPaymentAmount,
        long shortfallAmount
) {
}
