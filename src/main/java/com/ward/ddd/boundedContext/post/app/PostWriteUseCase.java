package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.boundedContext.post.out.PostRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.global.response.ResponseData;
import com.ward.ddd.shared.member.out.MemberApiClient;
import com.ward.ddd.shared.post.dto.PostDto;
import com.ward.ddd.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;
    private final MemberApiClient memberApiClient;


    public ResponseData<Post> write(PostMember member, String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(member)
                .build();

        postRepository.save(post);

        eventPublisher.publish(new PostCreatedEvent(PostDto.from(post)));

        String randomSecureTip = memberApiClient.getRandomSecureTip();

        return ResponseData.from(201, "%d번 글이 생성되었습니다. 보안 팁: %s".formatted(post.getId(), randomSecureTip), post);
    }
}
