package com.ward.ddd.shared.cash.event;

import com.ward.ddd.shared.market.dto.OrderDto;
import com.ward.ddd.standard.resultType.ResultType;

public record CashOrderPaymentFailedEvent(
        int code,
        String message,
        OrderDto orderDto,
        long pgPaymentAmount,
        long shortfallAmount
) implements ResultType {
}
