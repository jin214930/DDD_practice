package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostComment;
import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.boundedContext.post.out.PostMemberRepository;
import com.ward.ddd.boundedContext.post.out.PostRepository;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostMemberRepository postMemberRepository;
    private final PostWriteUseCase postWriteUseCase;
    private final PostCommentWriteUseCase postCommentWriteUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional
    public ResponseData<Post> write(Member member, String title, String content) {
        return postWriteUseCase.write(member, title, content);
    }

    @Transactional
    public ResponseData<PostComment> writeComment(Post post, Member member, String content) {
        return postCommentWriteUseCase.writeComment(post, member, content);
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시글입니다.")
        );
    }

    @Transactional
    public PostMember syncMember(MemberDto memberDto) {
        PostMember member = PostMember.builder()
                .id(memberDto.id())
                .createdDate(memberDto.createdDate())
                .modifiedDate(memberDto.modifiedDate())
                .username(memberDto.username())
                .password("")
                .nickname(memberDto.nickname())
                .activityScore(memberDto.ActivityScore())
                .build();

        return postMemberRepository.save(member);
    }
}
