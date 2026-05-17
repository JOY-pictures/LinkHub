package com.linkhub.linkhub.content.application.service;

import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.reactions.application.dto.PostReactionChangedEvent;
import com.linkhub.linkhub.reactions.application.port.ReactionSummaryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostCrystallizationListener {

    private final PostRepository postRepository;
    private final ReactionSummaryPort reactionSummaryPort;

    @Value("${linkhub.crystallization.threshold:50}")
    private int crystallizationThreshold;

    @EventListener
    public void handlePostReactionChanged(PostReactionChangedEvent event) {
        log.info("Получено событие изменения реакции для поста {}. Проверяем кристаллизацию...", event.postId());

        postRepository.findById(event.postId()).ifPresent(post -> {

            if (post.isModeLocked()) {
                return;
            }

            long totalReactionsCount = reactionSummaryPort.count(post.getId()).totalCount();

            if (totalReactionsCount >= crystallizationThreshold) {
                log.info("Пост {} набрал {} реакций. Кристаллизация активирована!", event.postId(), totalReactionsCount);

                post.crystallize();

                postRepository.save(post);

                // TODO: На следующем этапе здесь же можно будет запустить расчет communityOverride (500+ реакций)
                // и снижение trustScore автора!
            }

        });

    }
}
