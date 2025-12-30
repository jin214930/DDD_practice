package com.ward.ddd.boundedContext.market.app;

import com.ward.ddd.boundedContext.market.domain.MarketMember;
import com.ward.ddd.boundedContext.market.domain.Product;
import com.ward.ddd.boundedContext.market.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateProductUseCase {
    private final ProductRepository productRepository;

    public Product createProduct(MarketMember member, String sourceTypeCode, Long sourceId, String name, String description, long price, long salePrice) {
        Product product = Product.builder()
                .seller(member)
                .sourceTypeCode(sourceTypeCode)
                .sourceId(sourceId)
                .name(name)
                .description(description)
                .price(price)
                .salePrice(salePrice)
                .build();

        return productRepository.save(product);
    }
}
