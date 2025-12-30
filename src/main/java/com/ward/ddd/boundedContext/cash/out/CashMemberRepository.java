package com.ward.ddd.boundedContext.cash.out;

import com.ward.ddd.boundedContext.cash.domain.CashMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMemberRepository extends JpaRepository<CashMember, Long> {
}
