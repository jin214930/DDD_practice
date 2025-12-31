package com.ward.ddd.boundedContext.payout.out;

import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutMemberRepository extends JpaRepository<PayoutMember, Long> {
}
