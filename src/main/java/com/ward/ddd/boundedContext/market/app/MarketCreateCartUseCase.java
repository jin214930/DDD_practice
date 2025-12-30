package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.Cart;
import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.out.CartRepository;
import com.ward.ddd.boundedContext.market.out.MarketMemberRepository;
import com.ward.ddd.shared.market.dto.MarketMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateCartUseCase {
    private final CartRepository cartRepository;
    private final MarketMemberRepository marketMemberRepository;

    public Cart createCart(MarketMemberDto memberDto) {
        MarketMember buyer = marketMemberRepository.getReferenceById(memberDto.id());

        Cart cart = Cart.builder()
                .id(buyer.getId())
                .buyer(buyer)
                .build();

        return cartRepository.save(cart);
    }
}
