package com.linkhub.linkhub.feed.application.ranking;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import org.springframework.stereotype.Component;

@Component
public class MotivationScorer implements PostRankingScorer{
    @Override
    public boolean supports(String modeName) {
        return "Motivation".equalsIgnoreCase(modeName);
    }

    @Override
    public double calculateScore (PostReactionSummary reactions) {
        return (reactions.inspiringCount() * 2.0) + (reactions.usefulCount() * 1.0);
    }
}
