package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.Cart;
import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.domain.Product;
import com.ward.ddd.shared.market.dto.MarketMemberDto;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;
    private final MarketCreateProductUseCase marketCreateProductUseCase;
    private final MarketCreateCartUseCase marketCreateCartUseCase;
    private final MarketSupport marketSupport;

    @Transactional
    public MarketMember syncMember(MemberDto memberDto) {
        return marketSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional(readOnly = true)
    public long productsCount() {
        return marketSupport.productsCount();
    }

    @Transactional(readOnly = true)
    public MarketMember findMemberByUsername(String username) {
        return marketSupport.findMemberByUsername(username);
    }

    @Transactional
    public Product createProduct(MarketMember member, String sourceTypeCode, Long sourceId, String name, String description, long price, long salePrice) {
        return marketCreateProductUseCase.createProduct(member, sourceTypeCode, sourceId, name, description, price, salePrice);
    }

    @Transactional(readOnly = true)
    public Cart findCartByBuyer(MarketMember member) {
        return marketSupport.findCartByBuyer(member);
    }

    @Transactional(readOnly = true)
    public Product findProductById(long id) {
        return marketSupport.findProductById(id);
    }

    @Transactional
    public Cart createCart(MarketMemberDto memberDto) {
        return marketCreateCartUseCase.createCart(memberDto);
    }
}
