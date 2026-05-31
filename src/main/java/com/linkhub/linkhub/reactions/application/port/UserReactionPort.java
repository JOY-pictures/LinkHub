package com.linkhub.linkhub.reactions.application.port;

import com.linkhub.linkhub.reactions.domain.ReactionType;
import com.linkhub.linkhub.reactions.domain.ReactionView;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface UserReactionPort {
    Optional<ReactionView> getReactionByUserIdAndPostId(Long userId, Long postId);
    Map<Long, ReactionType> getReactionsByUserIdAndPostIds(Long userId, Collection<Long> postIds);
}
