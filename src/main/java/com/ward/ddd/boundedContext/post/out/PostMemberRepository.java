package com.ward.ddd.boundedContext.post.out;

import com.ward.ddd.boundedContext.post.domain.PostMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMemberRepository extends JpaRepository<PostMember, Long> {
}
