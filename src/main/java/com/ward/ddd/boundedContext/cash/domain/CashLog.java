package com.ward.ddd.boundedContext.cash.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CASH_CASH_LOG")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CashLog extends BaseIdAndTime {
    public enum EventType {
        충전__무통장입금,
        충전__PG결제_토스페이먼츠,
        출금__통장입금,
        사용__주문결제,
        임시보관__주문결제,
        정산지급__상품판매_수수료,
        정산수령__상품판매_수수료,
        정산지급__상품판매_대금,
        정산수령__상품판매_대금,
    }

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String relatedTypeCode;

    private Long relatedId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder;

    @ManyToOne
    private Wallet wallet;

    private long amount;

    private long balance;
}
