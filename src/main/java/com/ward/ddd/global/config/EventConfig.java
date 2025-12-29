package com.ward.ddd.global.config;

import com.ward.ddd.global.event.EventPublisher;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    @Getter
    private static EventPublisher eventPublisher;

    @Autowired
    public void setEventPublisher(EventPublisher eventPublisher) {
        EventConfig.eventPublisher = eventPublisher;
    }
}
