package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.boundedContext.payout.domain.PayoutCandidateItem;
import com.ward.ddd.boundedContext.payout.domain.PayoutEventType;
import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import com.ward.ddd.boundedContext.payout.out.PayoutCandidateItemRepository;
import com.ward.ddd.shared.market.dto.OrderDto;
import com.ward.ddd.shared.market.dto.OrderItemDto;
import com.ward.ddd.shared.market.out.MarketApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {
    private final MarketApiClient marketApiClient;
    private final PayoutCandidateItemRepository payoutCandidateItemRepository;
    private final PayoutSupport payoutSupport;

    public void addPayoutCandidateItems(OrderDto orderDto) {
        List<OrderItemDto> items = marketApiClient.getOrderItems(orderDto.id());

        items.forEach(item -> makePayoutCandidateItems(orderDto, item));
    }

    private void makePayoutCandidateItems(OrderDto orderDto, OrderItemDto item) {
        PayoutMember system = payoutSupport.findSystemMember();
        PayoutMember buyer = payoutSupport.findMemberById(orderDto.buyerId());
        PayoutMember seller = payoutSupport.findMemberById(item.sellerId());

        System.out.println(item.payoutFee() + " " + item.salePriceWithoutFee() + "\n");

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_수수료,
                item.getModelTypeCode(),
                item.id(),
                orderDto.paymentDate(),
                buyer,
                system,
                item.payoutFee()
        );

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_대금,
                item.getModelTypeCode(),
                item.id(),
                orderDto.paymentDate(),
                buyer,
                seller,
                item.salePriceWithoutFee()
        );
    }

    private void makePayoutCandidateItem(
            PayoutEventType eventType,
            String relatedTypeCode,
            long relatedId,
            LocalDateTime paymentDate,
            PayoutMember payer,
            PayoutMember payee,
            long amount
    ) {
        PayoutCandidateItem payoutCandidateItem = PayoutCandidateItem
                .builder()
                .eventType(eventType)
                .relatedTypeCode(relatedTypeCode)
                .relatedId(relatedId)
                .paymentDate(paymentDate)
                .payer(payer)
                .payee(payee)
                .amount(amount)
                .build();

        payoutCandidateItemRepository.save(payoutCandidateItem);
    }
}
