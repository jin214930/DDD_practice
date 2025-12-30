package com.ward.ddd.shared.market.event;

import com.ward.ddd.shared.market.dto.OrderDto;

public record MarketOrderPaymentRequestedEvent(
        OrderDto orderDto,
        long pgPaymentAmount
) {
}
