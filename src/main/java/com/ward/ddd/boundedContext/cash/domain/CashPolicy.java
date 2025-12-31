package com.ward.ddd.boundedContext.cash.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CashPolicy {
    public static long HOLDING_MEMBER_ID;

    @Value("${custom.global.holdingMemberId}")
    public void setHoldingMemberId(long id) {
        HOLDING_MEMBER_ID = id;
    }
}
