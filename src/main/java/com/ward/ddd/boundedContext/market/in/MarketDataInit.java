package com.ward.ddd.boundedContext.market.in;

import com.ward.ddd.boundedContext.member.app.MemberFacade;
import com.ward.ddd.shared.post.out.PostApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class MarketDataInit {
    private final MarketDataInit self;
    private final MemberFacade memberFacade;
    private final PostApiClient postApiClient;

    public MarketDataInit(@Lazy MarketDataInit self, MemberFacade memberFacade, PostApiClient postApiClient) {
        this.self = self;
        this.memberFacade = memberFacade;
        this.postApiClient = postApiClient;
    }

    @Bean
    @Order(3)
    public ApplicationRunner marketBaseInitDataRunner() {
        return args -> {
            self.makeBaseProducts();
        };
    }

    @Transactional
    public void makeBaseProducts() {
        postApiClient.getPosts()
                .forEach(post -> System.out.println("post.id: " + post.id()));
    }
}
