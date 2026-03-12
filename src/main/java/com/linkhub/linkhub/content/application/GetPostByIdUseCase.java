package com.linkhub.linkhub.content.application;

import com.linkhub.linkhub.content.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPostByIdUseCase {
    private final PostRepository postRepository;

    public Optional<PostView> getById (Long postId) {
        return postRepository.findById(postId).
                map(post -> new PostView(
                        post.getId(),
                        post.getAuthorId(),
                        post.getText(),
                        post.getCreatedAt().toString()
                ));
    }
}
