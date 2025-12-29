package com.ward.ddd.domain.post.service;

import com.ward.ddd.domain.member.entity.Member;
import com.ward.ddd.domain.post.entity.Post;
import com.ward.ddd.domain.post.repository.PostRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Dictionary;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

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

        member.increaseActivityScore(3);

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시글입니다.")
        );
    }
}
