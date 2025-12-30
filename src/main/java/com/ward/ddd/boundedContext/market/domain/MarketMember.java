package com.ward.ddd.boundedContext.market.domain;

import com.ward.ddd.shared.member.domain.ReplicaMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MarketMember extends ReplicaMember {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;
}
