package com.ward.ddd.shared.post.dto;

import com.ward.ddd.boundedContext.post.entity.Post;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long authorId,
        String authorName,
        String title,
        String content
) {
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent()
        );
    }
}
