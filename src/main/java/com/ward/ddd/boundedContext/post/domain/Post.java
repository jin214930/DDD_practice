package com.ward.ddd.boundedContext.post.domain;

import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POST_POST")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseIdAndTime {
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostMember author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PostComment> postComments = new ArrayList<>();

    public boolean hasComments() {
        return !postComments.isEmpty();
    }

    public PostComment addComment(PostMember author, String content) {
        PostComment comment = PostComment.builder()
                .content(content)
                .post(this)
                .author(author)
                .build();

        postComments.add(comment);

        return comment;
    }
}
