package com.ward.ddd.boundedContext.post.out;

import com.ward.ddd.boundedContext.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByOrderByIdDesc();
}
