package com.ward.ddd.boundedContext.cash.domain;


import com.ward.ddd.shared.member.domain.ReplicaMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CashMember extends ReplicaMember {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;
}
