package com.ward.ddd.boundedContext.payout.out;

import com.ward.ddd.boundedContext.payout.domain.Payout;
import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
    Optional<Payout> findByPayeeAndPayoutDateIsNull(PayoutMember payee);
}
