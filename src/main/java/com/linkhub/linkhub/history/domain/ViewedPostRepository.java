package com.linkhub.linkhub.history.domain;

import java.util.Optional;

public interface ViewedPostRepository {
    ViewedPost save(ViewedPost vp);
    Optional<ViewedPost> findByUserIdAndPostId(Long userId, Long postId);

}
