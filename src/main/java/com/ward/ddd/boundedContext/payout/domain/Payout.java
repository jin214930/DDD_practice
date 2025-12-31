package com.ward.ddd.boundedContext.payout.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PAYOUT_PAYOUT")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payout extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    @Setter
    private LocalDateTime payoutDate;

    private long amount;

    @OneToMany(mappedBy = "payout", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PayoutItem> items = new ArrayList<>();

    public PayoutItem addItem(PayoutEventType eventType, String relatedTypeCode, Long relatedId, LocalDateTime paymentDate, PayoutMember payer, PayoutMember payee, long amount) {
        PayoutItem payoutItem = PayoutItem.builder()
                .eventType(eventType)
                .relatedTypeCode(relatedTypeCode)
                .relatedId(relatedId)
                .paymentDate(paymentDate)
                .payer(payer)
                .payee(payee)
                .amount(amount)
                .build();

        items.add(payoutItem);

        return payoutItem;
    }
}
