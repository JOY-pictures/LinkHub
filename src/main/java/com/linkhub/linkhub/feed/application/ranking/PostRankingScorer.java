package com.linkhub.linkhub.feed.application.ranking;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;

public interface PostRankingScorer {
    boolean supports(String modeName);
    double calculateScore(PostReactionSummary summary);
}
