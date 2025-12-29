package com.ward.ddd.boundedContext.post.service;

import com.ward.ddd.boundedContext.member.entity.Member;
import com.ward.ddd.boundedContext.post.entity.Post;
import com.ward.ddd.boundedContext.post.repository.PostRepository;
import com.ward.ddd.global.event.EventPublisher;
import com.ward.ddd.global.exception.DomainException;
import com.ward.ddd.shared.post.dto.PostDto;
import com.ward.ddd.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional
    public Post write(Member member, String title, String content) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(member)
                .build();

        eventPublisher.publish(new PostCreatedEvent(PostDto.from(post)));

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시글입니다.")
        );
    }
}
