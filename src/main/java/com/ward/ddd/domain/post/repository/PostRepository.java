package com.ward.ddd.domain.post.repository;

import com.ward.ddd.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
