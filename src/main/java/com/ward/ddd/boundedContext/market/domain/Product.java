package com.ward.ddd.boundedContext.market.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MARKET_PRODUCT")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseIdAndTime {
    private String sourceTypeCode;

    private Long sourceId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private long price;

    private long salePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember seller;
}
