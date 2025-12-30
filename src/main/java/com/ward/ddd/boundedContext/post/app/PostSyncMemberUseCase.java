package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.boundedContext.post.out.PostMemberRepository;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSyncMemberUseCase {
    private final PostMemberRepository postMemberRepository;

    public PostMember syncMember(MemberDto memberDto) {
        PostMember member = PostMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.activityScore())
                .build();

        return postMemberRepository.save(member);
    }
}
