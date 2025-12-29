package com.ward.ddd.global.entity;

import com.ward.ddd.global.config.EventConfig;
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
public abstract class BaseEntity {
    public abstract Long getId();

    public abstract LocalDateTime getCreatedDate();

    public abstract LocalDateTime getModifiedDate();

    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }

    protected void publishEvent(Object event) {
        EventConfig.getEventPublisher().publish(event);
    }
}
