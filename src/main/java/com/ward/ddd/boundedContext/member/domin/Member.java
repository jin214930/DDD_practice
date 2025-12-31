package com.ward.ddd.boundedContext.member.domin;

import com.ward.ddd.shared.member.domain.SourceMember;
import com.ward.ddd.shared.member.dto.MemberDto;
import com.ward.ddd.shared.member.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MEMBER_MEMBER")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends SourceMember {
    public int increaseActivityScore(int amount) {
        if (amount == 0)
            return getActivityScore();

        setActivityScore(getActivityScore() + amount);

        publishEvent(new MemberModifiedEvent(MemberDto.from(this)));

        return getActivityScore();
    }
}
