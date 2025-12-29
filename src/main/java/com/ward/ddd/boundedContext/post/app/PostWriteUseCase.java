package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.member.domin.Member;
import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.out.PostRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.shared.post.dto.PostDto;
import com.ward.ddd.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;


    public Post write(Member member, String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(member)
                .build();

        eventPublisher.publish(new PostCreatedEvent(PostDto.from(post)));

        return postRepository.save(post);
    }
}
