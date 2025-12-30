package com.ward.ddd.shared.cash.event;

import com.ward.ddd.shared.cash.dto.CashMemberDto;

public record CashMemberCreatedEvent(
        CashMemberDto memberDto
) {
}
