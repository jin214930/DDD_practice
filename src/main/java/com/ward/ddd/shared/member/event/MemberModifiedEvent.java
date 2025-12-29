package com.ward.ddd.shared.member.event;

import com.ward.ddd.shared.member.dto.MemberDto;

public record MemberModifiedEvent(
        MemberDto memberDto
) {
}
