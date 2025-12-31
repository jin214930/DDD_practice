package com.ward.ddd.shared.member.domain;

import com.ward.ddd.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;
}
