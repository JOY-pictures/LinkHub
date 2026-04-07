package com.linkhub.linkhub.content.application.service;

import com.linkhub.linkhub.content.application.model.PostSummary;
import com.linkhub.linkhub.content.application.port.PostInformationPort;
import com.linkhub.linkhub.content.domain.PostRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostInformationService implements PostInformationPort {

    private final PostRepository postRepository;

    @Override
    public boolean existsById(Long postId) {
        return postRepository.existsById(postId);
    }

    @Override
    public Optional<PostSummary> findById(Long postId) {
        return postRepository.findById(postId).map(post -> {
            return new PostSummary(
                    post.getId(),
                    post.getAuthorId(),
                    post.getContent(),
                    post.getCreatedAt()
            );
        });
    }
}
