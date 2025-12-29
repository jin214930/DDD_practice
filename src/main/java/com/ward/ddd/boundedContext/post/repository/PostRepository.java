package com.ward.ddd.boundedContext.post.repository;

import com.ward.ddd.boundedContext.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
