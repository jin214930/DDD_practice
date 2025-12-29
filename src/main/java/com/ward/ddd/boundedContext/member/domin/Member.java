package com.ward.ddd.boundedContext.member.domin;

import com.ward.ddd.shared.member.domain.SourceMember;
import com.ward.ddd.shared.member.dto.MemberDto;
import com.ward.ddd.shared.member.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "MEMBER_MEMBER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends SourceMember {
    private String username;

    private String password;

    private String nickname;

    private int activityScore;

    public int increaseActivityScore(int amount) {
        if (amount == 0)
            return getActivityScore();

        activityScore = activityScore + amount;

        publishEvent(new MemberModifiedEvent(MemberDto.from(this)));

        return activityScore;
    }
}
