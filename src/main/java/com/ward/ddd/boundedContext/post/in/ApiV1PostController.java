package com.ward.ddd.boundedContext.post.in;

import com.ward.ddd.boundedContext.post.app.PostFacade;
import com.ward.ddd.shared.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostFacade postFacade;

    @GetMapping
    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postFacade.findByOrderByIdDesc().stream()
                .map(PostDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostDto getPost(@PathVariable long id) {
        return PostDto.from(postFacade.findById(id));
    }
}
