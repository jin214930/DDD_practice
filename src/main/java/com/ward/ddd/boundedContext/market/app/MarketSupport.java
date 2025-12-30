package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.Cart;
import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.domain.Product;
import com.ward.ddd.boundedContext.market.out.CartRepository;
import com.ward.ddd.boundedContext.market.out.MarketMemberRepository;
import com.ward.ddd.boundedContext.market.out.OrderRepository;
import com.ward.ddd.boundedContext.market.out.ProductRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketSupport {
    private final ProductRepository productRepository;
    private final MarketMemberRepository marketMemberRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public long productsCount() {
        return productRepository.count();
    }

    public MarketMember findMemberByUsername(String username) {
        return marketMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 아이디입니다.")
        );
    }

    public Cart findCartByBuyer(MarketMember member) {
        return cartRepository.findByBuyer(member).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.")
        );

    }

    public Product findProductById(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 상품입니다.")
        );
    }

    public long ordersCount() {
        return orderRepository.count();
    }
}
