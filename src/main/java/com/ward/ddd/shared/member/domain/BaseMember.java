package com.ward.ddd.shared.member.domain;

import com.ward.ddd.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;
}
