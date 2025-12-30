package com.ward.ddd.boundedContext.post.in;

import com.ward.ddd.boundedContext.post.app.PostFacade;
import com.ward.ddd.boundedContext.post.domain.Post;
import com.ward.ddd.boundedContext.post.domain.PostMember;
import com.ward.ddd.global.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class PostDataInit {
    private final PostDataInit self;
    private final PostFacade postFacade;

    public PostDataInit(@Lazy PostDataInit self, PostFacade postFacade) {
        this.self = self;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postBaseInitDataRunner() {
        return args -> {
            self.makeBasePosts();
            self.makeBasePostComments();
        };
    }


    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        PostMember user1Member = postFacade.findMemberByUsername("user1");
        PostMember user2Member = postFacade.findMemberByUsername("user2");
        PostMember user3Member = postFacade.findMemberByUsername("user3");

        ResponseData<Post> post1RsData = postFacade.write(user1Member, "제목1", "내용1");
        log.debug(post1RsData.message());

        ResponseData<Post> post2RsData = postFacade.write(user1Member, "제목2", "내용2");
        log.debug(post2RsData.message());

        ResponseData<Post> post3RsData = postFacade.write(user1Member, "제목3", "내용3");
        log.debug(post3RsData.message());

        ResponseData<Post> post4RsData = postFacade.write(user2Member, "제목4", "내용4");
        log.debug(post4RsData.message());

        ResponseData<Post> post5RsData = postFacade.write(user2Member, "제목5", "내용5");
        log.debug(post5RsData.message());

        ResponseData<Post> post6RsData = postFacade.write(user3Member, "제목6", "내용6");
        log.debug(post6RsData.message());
    }

    @Transactional
    public void makeBasePostComments() {
        Post post1 = postFacade.findById(1);
        Post post2 = postFacade.findById(2);
        Post post3 = postFacade.findById(3);
        Post post4 = postFacade.findById(4);
        Post post5 = postFacade.findById(5);
        Post post6 = postFacade.findById(6);

        PostMember user1Member = postFacade.findMemberByUsername("user1");
        PostMember user2Member = postFacade.findMemberByUsername("user2");
        PostMember user3Member = postFacade.findMemberByUsername("user3");

        if (post1.hasComments()) return;

        postFacade.writeComment(post1, user1Member, "댓글1");
        postFacade.writeComment(post1, user2Member, "댓글2");
        postFacade.writeComment(post1, user3Member, "댓글3");

        if (post2.hasComments()) return;

        postFacade.writeComment(post2, user2Member, "댓글4");
        postFacade.writeComment(post2, user2Member, "댓글5");

        if (post3.hasComments()) return;

        postFacade.writeComment(post3, user3Member, "댓글6");
        postFacade.writeComment(post3, user3Member, "댓글7");
        postFacade.writeComment(post4, user1Member, "댓글8");
    }
}
