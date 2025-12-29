package com.ward.ddd.boundedContext.member.domin;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseIdAndTime {
    private String username;

    private String password;

    private String nickname;

    private int activityScore;

    public int increaseActivityScore(int amount) {
        return activityScore += amount;
    }
}
