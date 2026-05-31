package com.linkhub.linkhub.reactions.application.usecase;

import com.linkhub.linkhub.reactions.application.dto.ReactionView;
import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import com.linkhub.linkhub.reactions.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetReactionsByPostIds {
    private final ReactionRepository repository;

    @Transactional(readOnly = true)
    public List<PostReactionSummary> 
}
