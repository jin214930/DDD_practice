package com.ward.ddd.boundedContext.payout.app;

import com.ward.ddd.boundedContext.payout.domain.*;
import com.ward.ddd.boundedContext.payout.out.PayoutCandidateItemRepository;
import com.ward.ddd.boundedContext.payout.out.PayoutRepository;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayoutCollectPayoutItemsMoreUseCase {
    private final PayoutRepository payoutRepository;
    private final PayoutCandidateItemRepository payoutCandidateItemRepository;

    public ResponseData<Integer> collectPayoutItemsMore(int limit) {
        List<PayoutCandidateItem> payoutReadyCandidateItems = findPayoutReadyCandidateItems(limit);

        if (payoutReadyCandidateItems.isEmpty()) {
            return ResponseData.from(HttpStatus.OK.value(), "더 이상 정산할 항목이 없습니다.", 0);
        }

        payoutReadyCandidateItems.stream()
                .collect(Collectors.groupingBy(PayoutCandidateItem::getPayee))
                .forEach((payee, candidateItems) -> {
                    Payout payout = findActiveByPayee(payee);

                    candidateItems.forEach(item -> {
                        PayoutItem payoutItem = payout.addItem(
                                item.getEventType(),
                                item.getRelatedTypeCode(),
                                item.getRelatedId(),
                                item.getPaymentDate(),
                                item.getPayer(),
                                item.getPayee(),
                                item.getAmount()
                        );

                        item.setPayoutItem(payoutItem);
                    });
                });

        return ResponseData.from(
                HttpStatus.CREATED.value(),
                "%d건의 정산 데이터가 생성되었습니다.".formatted(payoutReadyCandidateItems.size()),
                payoutReadyCandidateItems.size()
        );
    }

    private Payout findActiveByPayee(PayoutMember payee) {
        return payoutRepository.findByPayeeAndPayoutDateIsNull(payee).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 정산데이터입니다.")
        );
    }

    private List<PayoutCandidateItem> findPayoutReadyCandidateItems(int limit) {
        LocalDateTime daysAgo = LocalDateTime
                .now()
                .minusDays(PayoutPolicy.PAYOUT_READY_WAITING_DAYS)
                .toLocalDate()
                .atStartOfDay();

        return payoutCandidateItemRepository.findByPayoutItemIsNullAndPaymentDateBeforeOrderByPayeeAscIdAsc(
                daysAgo,
                PageRequest.of(0, limit)
        );
    }
}
