package com.ward.ddd.shared.post.event;

import com.ward.ddd.shared.post.dto.PostCommentDto;

public record PostCommentCreatedEvent(
        PostCommentDto postCommentDto
) {
}
