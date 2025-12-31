package com.ward.ddd.shared.cash.out;

import com.ward.ddd.shared.cash.dto.WalletDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CashApiClient {
    private final RestClient restClient;

    public CashApiClient(@Value("${custom.global.internalBackUrl}") String internalBackUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(internalBackUrl + "/api/v1/cash")
                .build();
    }

    public WalletDto getItemByHolderId(long holderId) {
        return restClient.get()
                .uri("/wallets/by-holder/" + holderId)
                .retrieve()
                .body(WalletDto.class);
    }

    public long getBalanceByHolderId(long holderId) {
        WalletDto walletDto = getItemByHolderId(holderId);
        return walletDto.balance();
    }
}
