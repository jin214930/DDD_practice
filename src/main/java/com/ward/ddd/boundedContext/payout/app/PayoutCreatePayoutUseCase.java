package com.ward.ddd.boundedContext.payout.app;


import com.ward.ddd.boundedContext.payout.domain.Payout;
import com.ward.ddd.boundedContext.payout.domain.PayoutMember;
import com.ward.ddd.boundedContext.payout.out.PayoutMemberRepository;
import com.ward.ddd.boundedContext.payout.out.PayoutRepository;
import com.ward.ddd.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutCreatePayoutUseCase {
    private final PayoutRepository payoutRepository;
    private final PayoutMemberRepository payoutMemberRepository;

    public Payout createPayout(PayoutMemberDto memberDto) {
        PayoutMember payee = payoutMemberRepository.getReferenceById(memberDto.id());

        Payout payout = Payout.builder()
                .payee(payee)
                .build();

        payoutRepository.save(payout);

        return payout;
    }
}
