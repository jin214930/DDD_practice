package com.ward.ddd.boundedContext.post.app;

import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.boundedContext.post.out.PostMemberRepository;
import com.ward.ddd.boundedContext.post.out.PostRepository;
import com.ward.ddd.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSupport {
    private final PostMemberRepository postMemberRepository;
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public PostMember findMemberByUsername(String username) {
        return postMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 회원입니다.")
        );
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시글입니다.")
        );
    }
}
