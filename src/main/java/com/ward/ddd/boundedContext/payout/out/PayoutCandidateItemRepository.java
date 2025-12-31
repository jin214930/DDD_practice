package com.ward.ddd.boundedContext.payout.out;

import com.ward.ddd.boundedContext.payout.domain.PayoutCandidateItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutCandidateItemRepository extends JpaRepository<PayoutCandidateItem, Long> {
}
