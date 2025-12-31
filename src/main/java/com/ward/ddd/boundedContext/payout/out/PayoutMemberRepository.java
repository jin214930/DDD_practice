package com.ward.ddd.boundedContext.payout.out;

import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayoutMemberRepository extends JpaRepository<PayoutMember, Long> {
    Optional<PayoutMember> findByUsername(String system);
}
