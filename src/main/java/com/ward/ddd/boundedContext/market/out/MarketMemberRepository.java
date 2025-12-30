package com.ward.ddd.boundedContext.market.out;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketMemberRepository extends JpaRepository<MarketMember, Long> {
}
