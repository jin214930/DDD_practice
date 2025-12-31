package com.ward.ddd.shared.market.dto;

import com.ward.ddd.boundedContext.market.domain.Order;
import com.ward.ddd.standard.modelType.HasModelTypeCode;

import java.time.LocalDateTime;

public record OrderDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long buyerId,
        String buyerName,
        long price,
        long salePrice,
        LocalDateTime requestPaymentDate,
        LocalDateTime paymentDate
) implements HasModelTypeCode {
    public static OrderDto from(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCreatedDate(),
                order.getModifiedDate(),
                order.getBuyer().getId(),
                order.getBuyer().getNickname(),
                order.getPrice(),
                order.getSalePrice(),
                order.getRequestPaymentDate(),
                order.getPaymentDate()
        );
    }

    @Override
    public String getModelTypeCode() {
        return "Order";
    }
}
