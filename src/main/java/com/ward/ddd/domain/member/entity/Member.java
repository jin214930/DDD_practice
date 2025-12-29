package com.ward.ddd.domain.member.entity;

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
}
