package com.linkhub.linkhub.feed.application.ranking;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import org.springframework.stereotype.Component;

@Component
public class LearningScorer implements PostRankingScorer{
    @Override
    public boolean supports(String modeName) {
        return "Learning".equalsIgnoreCase(modeName);
    }

    @Override
    public double calculateScore (PostReactionSummary reactions) {
        return (reactions.usefulCount() * 2.0) + (reactions.inspiringCount() * 1.0);
    }
}
