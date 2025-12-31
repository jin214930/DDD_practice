package com.ward.ddd.boundedContext.payout.domain;

import com.ward.ddd.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayoutMember extends ReplicaMember {
}
