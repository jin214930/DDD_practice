package com.ward.ddd.shared.market.event;

import com.ward.ddd.shared.market.dto.MarketMemberDto;

public record MarketMemberCreatedEvent(
        MarketMemberDto memberDto
) {
}
