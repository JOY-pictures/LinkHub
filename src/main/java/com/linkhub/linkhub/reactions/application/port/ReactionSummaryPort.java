package com.linkhub.linkhub.reactions.application.port;

import com.linkhub.linkhub.reactions.domain.PostReactionSummary;

import java.util.Collection;
import java.util.Map;

public interface ReactionSummaryPort {
    PostReactionSummary getReactionSummaryByPostId (Long postId);
    Map<Long, PostReactionSummary> getReactionSummariesByPostIds(Collection<Long> postIds);
}