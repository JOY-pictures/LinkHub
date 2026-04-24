package com.linkhub.linkhub.content.domain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(Long id);
    boolean existsById(Long id);
    List<Post> findPostsByModeIdWithLimit(Long modeId, int limit);
}
