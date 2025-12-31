package com.ward.ddd.shared.payout.event;

import com.ward.ddd.shared.payout.dto.PayoutMemberDto;

public record PayoutMemberCreatedEvent(
        PayoutMemberDto memberDto
) {
}
