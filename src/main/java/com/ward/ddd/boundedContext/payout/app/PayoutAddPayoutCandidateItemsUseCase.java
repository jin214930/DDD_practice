package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.shared.market.dto.OrderDto;
import com.ward.ddd.shared.market.dto.OrderItemDto;
import com.ward.ddd.shared.market.out.MarketApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {
    private final MarketApiClient marketApiClient;

    public void addPayoutCandidateItems(OrderDto orderDto) {
        List<OrderItemDto> items = marketApiClient.getOrderItems(orderDto.id());

        items.forEach(item -> {
            log.debug("orderItem.id: {}", item.id());
        });
    }
}
