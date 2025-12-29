package com.ward.ddd.shared.member.domain;

import com.ward.ddd.global.entity.BaseEntity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ReplicaMember extends BaseEntity {
    @Id
    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
