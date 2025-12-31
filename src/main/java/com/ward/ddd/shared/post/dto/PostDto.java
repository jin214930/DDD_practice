package com.ward.ddd.shared.post.dto;

import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.standard.modelType.HasModelTypeCode;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long authorId,
        String authorName,
        String title,
        String content
) implements HasModelTypeCode {
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

    @Override
    public String getModelTypeCode() {
        return "Post";
    }
}
