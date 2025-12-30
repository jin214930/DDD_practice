package com.ward.ddd.boundedContext.market.out;

import com.ward.ddd.boundedContext.market.domain.Cart;
import com.ward.ddd.boundedContext.market.domain.MarketMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByBuyer(MarketMember member);
}
