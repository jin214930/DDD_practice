package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.shared.market.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {
    public void addPayoutCandidateItems(OrderDto orderDto) {
        log.debug("addPayoutCandidateItems.orderId: {}", orderDto.id());
    }
}
