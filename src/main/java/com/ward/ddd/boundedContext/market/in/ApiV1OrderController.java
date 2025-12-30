package com.ward.ddd.boundedContext.market.in;

import com.ward.ddd.boundedContext.market.app.MarketFacade;
import com.ward.ddd.boundedContext.market.domain.Order;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.cash.out.CashApiClient;
import com.ward.ddd.shared.market.out.TossPaymentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/market/orders")
@RequiredArgsConstructor
public class ApiV1OrderController {
    private final MarketFacade marketFacade;
    private final TossPaymentsService tossPaymentsService;
    private final CashApiClient cashApiClient;

    public record ConfirmPaymentByTossPaymentsRequest(
            @NotBlank String paymentKey,
            @NotBlank String orderId,
            @NotNull long amount
    ) {
    }

    @CrossOrigin(
            origins = {
                    "https://cdpn.io",
                    "https://codepen.io"
            },
            allowedHeaders = "*",
            methods = {RequestMethod.POST}
    )
    @PostMapping("/{id}/payment/confirm/by/tossPayments")
    @Transactional
    public ResponseData<Void> confirmPaymentByTossPayments(
            @PathVariable long id,
            @Valid @RequestBody ConfirmPaymentByTossPaymentsRequest request
    ) {
        Order order = marketFacade.findOrderById(id);

        if (order.isCanceled()) {
            throw new DomainException(HttpStatus.BAD_REQUEST.value(), "이미 취소된 주문입니다.");
        }

        if (order.isPaymentInProgress()) {
            throw new DomainException(HttpStatus.BAD_REQUEST.value(), "이미 결제 중인 주문입니다.");
        }

        if (order.isPaid()) {
            throw new DomainException(HttpStatus.BAD_REQUEST.value(), "이미 결제된 주문입니다.");
        }

        long walletBalance = cashApiClient.getBalanceByHolderId(order.getBuyer().getId());

        if (order.getSalePrice() > walletBalance + request.amount()) {
            throw new DomainException(HttpStatus.BAD_REQUEST.value(), "결제를 완료하기에 결제 금액이 부족합니다.");
        }

        if (order.getId() != Long.parseLong(request.orderId().split("-", 3)[1])) {
            throw new DomainException(HttpStatus.BAD_REQUEST.value(), "주문 번호가 일치하지 않습니다.");
        }

        tossPaymentsService.confirmCardPayment(request.paymentKey(), request.orderId(), request.amount());

        marketFacade.requestPayment(order, request.amount());

        return ResponseData.from(HttpStatus.ACCEPTED.value(), "결제 프로세스가 시작되었습니다.");
    }
}
