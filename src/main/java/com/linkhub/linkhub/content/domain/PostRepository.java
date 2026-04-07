package com.linkhub.linkhub.content.domain;

import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    boolean existsById(Long id);
}
