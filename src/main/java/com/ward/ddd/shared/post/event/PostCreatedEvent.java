package com.ward.ddd.shared.post.event;

import com.ward.ddd.shared.post.dto.PostDto;

public record PostCreatedEvent(
        PostDto postDto
) {
}
