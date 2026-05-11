package com.linkhub.linkhub.reactions.application.port;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;

import java.util.Optional;

public interface UserReactionPort {
    Optional<ReactionView> getReaction(Long userId, Long postId);
}
