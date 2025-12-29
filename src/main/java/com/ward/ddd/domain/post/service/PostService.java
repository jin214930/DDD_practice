package com.ward.ddd.domain.post.service;

import com.ward.ddd.domain.member.entity.Member;
import com.ward.ddd.domain.post.entity.Post;
import com.ward.ddd.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return postRepository.save(post);
    }
}
