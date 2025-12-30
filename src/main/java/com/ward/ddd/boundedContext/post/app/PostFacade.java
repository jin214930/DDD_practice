package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostComment;
import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostWriteUseCase postWriteUseCase;
    private final PostCommentWriteUseCase postCommentWriteUseCase;
    private final PostSyncMemberUseCase postSyncMemberUseCase;
    private final PostSupport postSupport;

    @Transactional(readOnly = true)
    public long count() {
        return postSupport.count();
    }

    @Transactional
    public ResponseData<Post> write(PostMember member, String title, String content) {
        return postWriteUseCase.write(member, title, content);
    }

    @Transactional
    public ResponseData<PostComment> writeComment(Post post, PostMember member, String content) {
        return postCommentWriteUseCase.writeComment(post, member, content);
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postSupport.findById(id);
    }

    @Transactional
    public PostMember syncMember(MemberDto memberDto) {
        return postSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional(readOnly = true)
    public PostMember findMemberByUsername(String username) {
        return postSupport.findMemberByUsername(username);
    }
}
