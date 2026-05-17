package com.linkhub.linkhub.feed.application.ranking;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import org.springframework.stereotype.Component;

@Component
public class BalancedScorer implements PostRankingScorer{
    @Override
    public boolean supports(String modeName) {
        return "Balanced".equalsIgnoreCase(modeName);
    }

    @Override
    public double calculateScore (PostReactionSummary reactions) {
        return (reactions.funnyCount() * 1.0) + (reactions.calmCount() * 1.0) + (reactions.usefulCount() * 1.0) + (reactions.inspiringCount() * 1.0);
    }
}
