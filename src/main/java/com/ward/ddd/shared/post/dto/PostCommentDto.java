package com.ward.ddd.shared.post.dto;

import com.ward.ddd.boundedContext.post.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate,
        Long postId,
        Long authorId,
        String authorName,
        String content
) {
    public static PostCommentDto from(PostComment postComment) {
        return new PostCommentDto(
                postComment.getId(),
                postComment.getCreatedDate(),
                postComment.getModifiedDate(),
                postComment.getPost().getId(),
                postComment.getAuthor().getId(),
                postComment.getAuthor().getNickname(),
                postComment.getContent()
        );
    }
}
