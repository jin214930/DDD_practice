package com.ward.ddd.boundedContext.payout.app;


import com.ward.ddd.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutCreatePayoutUseCase {
    public void createPayout(PayoutMemberDto memberDto) {
        log.debug("createPayout.payeeId: {}", memberDto.id());
    }
}
