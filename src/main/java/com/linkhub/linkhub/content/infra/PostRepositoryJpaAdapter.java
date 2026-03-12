package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.Post;
import com.linkhub.linkhub.content.domain.PostRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryJpaAdapter implements PostRepository {
    private final SpringDataPostJpaRepository jpa;

    @Override
    public Post save(Post post) {
        return jpa.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpa.findById(id);
    }
}
