package com.ward.ddd.boundedContext.market.out;

import com.ward.ddd.boundedContext.market.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
