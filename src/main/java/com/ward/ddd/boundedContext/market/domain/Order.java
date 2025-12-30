package com.ward.ddd.boundedContext.market.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import com.ward.ddd.shared.market.dto.OrderDto;
import com.ward.ddd.shared.market.event.MarketOrderPaymentRequestedEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_ORDER")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember buyer;

    private long price;

    private long salePrice;

    private LocalDateTime requestPaymentDate;

    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    public static Order from(Cart cart) {
        Order order = Order.builder()
                .buyer(cart.getBuyer())
                .build();

        cart.getItems().forEach(cartItem -> order.addItem(cartItem.getProduct()));

        return order;
    }

    public void addItem(Product product) {
        OrderItem orderItem = OrderItem.builder()
                .order(this)
                .product(product)
                .productName(product.getName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .build();

        items.add(orderItem);

        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public void completePayment() {
        paymentDate = LocalDateTime.now();
    }

    public void requestPayment(long pgPaymentAmount) {
        requestPaymentDate = LocalDateTime.now();

        publishEvent(new MarketOrderPaymentRequestedEvent(OrderDto.from(this), pgPaymentAmount));
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void cancelRequestPayment() {
        requestPaymentDate = null;
    }
}
