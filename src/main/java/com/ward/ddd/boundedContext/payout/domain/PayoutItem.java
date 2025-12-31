package com.ward.ddd.boundedContext.payout.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYOUT_PAYOUT_ITEM")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PayoutItem extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Payout payout;

    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;

    private String relatedTypeCode;

    private Long relatedId;

    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payer;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    private long amount;
}
