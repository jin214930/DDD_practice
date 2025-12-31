package com.ward.ddd.shared.market.dto;

import com.ward.ddd.boundedContext.market.domain.OrderItem;

import java.time.LocalDateTime;

public record OrderItemDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long orderId,
        Long buyerId,
        String buyerName,
        Long sellerId,
        String sellerName,
        Long productId,
        String productName,
        long price,
        long salePrice,
        double payoutRate
) {
    public static OrderItemDto from(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getCreatedDate(),
                orderItem.getModifiedDate(),
                orderItem.getOrder().getId(),
                orderItem.getOrder().getBuyer().getId(),
                orderItem.getOrder().getBuyer().getNickname(),
                orderItem.getProduct().getSeller().getId(),
                orderItem.getProduct().getSeller().getNickname(),
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getOrder().getPrice(),
                orderItem.getOrder().getSalePrice(),
                orderItem.getPayoutRate()
        );
    }
}
