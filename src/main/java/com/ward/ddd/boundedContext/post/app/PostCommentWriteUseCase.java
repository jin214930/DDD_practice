package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostComment;
import com.ward.ddd.boundedContext.post.out.PostRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.post.dto.PostCommentDto;
import com.ward.ddd.shared.post.event.PostCommentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public ResponseData<PostComment> writeComment(Post post, Member member, String content) {
        PostComment comment = post.addComment(member, content);

        postRepository.saveAndFlush(post);

        eventPublisher.publish(new PostCommentCreatedEvent(PostCommentDto.from(comment)));

        return new ResponseData<>(201, "%d번 댓글이 생성되었습니다.".formatted(comment.getId()), comment);
    }
}
