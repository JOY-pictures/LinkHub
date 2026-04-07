package com.linkhub.linkhub.content.application.port;

import com.linkhub.linkhub.content.application.model.PostSummary;

import java.util.Optional;

public interface PostInformationPort {
    boolean existsById(Long postId);
    Optional<PostSummary> findById(Long PostId);
}
