package com.linkhub.linkhub.feed.application.ranking;

import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import org.springframework.stereotype.Component;

@Component
public class FunScorer implements PostRankingScorer{
    @Override
    public boolean supports(String modeName) {
        return "Fun".equalsIgnoreCase(modeName);
    }

    @Override
    public double calculateScore (PostReactionSummary reactions) {
        return (reactions.funnyCount() * 2.0) + (reactions.calmCount() * 1.0);
    }
}
