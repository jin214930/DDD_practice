package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.out.MarketMemberRepository;
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

    public long productsCount() {
        return productRepository.count();
    }

    public MarketMember findMemberByUsername(String username) {
        return marketMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 아이디입니다.")
        );
    }
}
