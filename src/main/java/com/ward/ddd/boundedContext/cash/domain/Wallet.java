package com.ward.ddd.boundedContext.cash.domain;

import com.ward.ddd.global.entity.BaseEntity;
import com.ward.ddd.global.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CASH_WALLET")
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private CashMember holder;

    private long balance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CashLog> cashLogs = new ArrayList<>();

    public boolean hasBalance() {
        return balance > 0;
    }

    public void credit(long amount, CashLog.EventType eventType, String relatedTypeCode, long relatedId) {
        balance += amount;

        addCashLog(amount, eventType, relatedTypeCode, relatedId);
    }

    public void credit(long amount, CashLog.EventType eventType, BaseEntity relatedEntity) {
        credit(amount, eventType, relatedEntity.getModelTypeCode(), relatedEntity.getId());
    }

    public void credit(long amount, CashLog.EventType eventType) {
        credit(amount, eventType, holder);
    }

    public void debit(long amount, CashLog.EventType eventType, String relatedTypeCode, long relatedId) {
        balance -= amount;

        addCashLog(-amount, eventType, relatedTypeCode, relatedId);
    }

    public void debit(long amount, CashLog.EventType eventType, BaseEntity relatedEntity) {
        debit(amount, eventType, relatedEntity.getModelTypeCode(), relatedEntity.getId());
    }

    public void debit(long amount, CashLog.EventType eventType) {
        debit(amount, eventType, holder);
    }

    private CashLog addCashLog(long amount, CashLog.EventType eventType, String relatedTypeCode, long relatedId) {
        CashLog cashLog = new CashLog(
                eventType,
                relatedTypeCode,
                relatedId,
                holder,
                this,
                amount,
                balance
        );

        cashLogs.add(cashLog);

        return cashLog;
    }
}
