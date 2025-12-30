package com.ward.ddd.boundedContext.market.domain;

import com.ward.ddd.global.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_CART")
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember buyer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    private int itemsCount;

    public boolean hasItems() {
        return itemsCount > 0;
    }

    public void addItem(Product product) {
        CartItem cartItem = CartItem.builder()
                .cart(this)
                .product(product)
                .build();

        items.add(cartItem);
        itemsCount++;
    }
}
