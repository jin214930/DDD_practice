package com.ward.ddd.boundedContext.post.entity;

import com.ward.ddd.boundedContext.member.entity.Member;
import com.ward.ddd.global.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseIdAndTime {
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final List<PostComment> postComments = new ArrayList<>();

    public boolean hasComments() {
        return !postComments.isEmpty();
    }

    public PostComment addComment(Member author, String content) {
        PostComment comment = PostComment.builder()
                .content(content)
                .post(this)
                .author(author)
                .build();

        postComments.add(comment);

        author.increaseActivityScore(1);

        return comment;
    }
}
