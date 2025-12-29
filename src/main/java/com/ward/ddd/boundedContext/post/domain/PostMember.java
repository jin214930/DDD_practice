package com.ward.ddd.boundedContext.post.domain;

import com.ward.ddd.global.entity.BaseIdAndTimeManual;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostMember extends BaseIdAndTimeManual {
    @Column(unique = true)
    private String username;

    private String password;

    private String nickname;

    private int activityScore;
}
