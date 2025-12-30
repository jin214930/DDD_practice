package com.ward.ddd.boundedContext.market.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "MARKET_ORDER_ITEM")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String productName;

    private long price;

    private long salePrice;

    @Builder.Default
    private double payoutRate = MarketPolicy.PRODUCT_PAYOUT_RATE;
}
