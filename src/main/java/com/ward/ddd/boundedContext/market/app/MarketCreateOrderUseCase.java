package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.Cart;
import com.ward.ddd.boundedContext.market.domain.Order;
import com.ward.ddd.boundedContext.market.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateOrderUseCase {
    private final OrderRepository orderRepository;

    public Order createOrder(Cart cart) {
        Order order = Order.from(cart);

        return orderRepository.save(order);
    }
}
