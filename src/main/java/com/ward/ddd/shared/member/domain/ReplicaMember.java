package com.ward.ddd.shared.member.domain;

import com.ward.ddd.global.entity.BaseEntity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class ReplicaMember extends BaseEntity {
    @Id
    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
