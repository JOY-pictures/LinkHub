package com.linkhub.linkhub.reactions.application.port;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;

public interface ReactionSummaryPort {
    PostReactionSummary count (Long postId);
}
