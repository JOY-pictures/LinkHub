package com.linkhub.linkhub.history.application.usecase;

import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import com.linkhub.linkhub.history.infra.ViewedPostEntity;
import com.linkhub.linkhub.reactions.application.dto.PostReactionChangedEvent;
import com.linkhub.linkhub.reactions.application.model.PostReactionSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AddToHistoryByReactionUseCase {

    private final ViewedPostRepository repository;

    @EventListener
    @Transactional
    public void addByReaction(PostReactionChangedEvent event) {
        Optional<ViewedPost> optVp = repository.findByUserIdAndPostId(event.userId(), event.postId());
        if (optVp.isEmpty()) {
            ViewedPost vp = ViewedPost.create(
                    event.userId(),
                    event.postId(),
                    event.changedAt()
            );
            repository.save(vp);
            log.info("Пользователь поставил реакцию. Сохранение поста в историю");
        }

    }
}
