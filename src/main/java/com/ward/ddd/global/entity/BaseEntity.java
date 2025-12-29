package com.ward.ddd.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    public abstract Long getId();

    public abstract LocalDateTime getCreatedDate();

    public abstract LocalDateTime getModifiedDate();

    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }
}
