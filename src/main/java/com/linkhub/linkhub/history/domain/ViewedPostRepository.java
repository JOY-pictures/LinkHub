package com.linkhub.linkhub.history.domain;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ViewedPostRepository {
    ViewedPost save(ViewedPost vp);
    List<Long> findPostIdsByUserId(Long userId, int limit);
    Optional<ViewedPost> findByUserIdAndPostId(Long userId, Long postId);
    List<ViewedPost> findPostsByUserId(Long userId, int limit);
}
