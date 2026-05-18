package com.linkhub.linkhub.history.application.usecase;

import com.linkhub.linkhub.history.application.dto.AddToHistoryCommand;
import com.linkhub.linkhub.history.application.dto.AddToHistoryResult;
import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddToHistoryByViewUseCase {

    private final ViewedPostRepository repository;

    @Transactional
    public AddToHistoryResult addByView(AddToHistoryCommand command) {
        ViewedPost returnVp;
        Optional<ViewedPost> optVp = repository.findByUserIdAndPostId(command.userId(), command.postId());
        if (optVp.isEmpty()) {
            ViewedPost vp = ViewedPost.create(
                    command.userId(),
                    command.postId(),
                    command.viewedAt()
            );
            returnVp = repository.save(vp);

        }
        else {
            returnVp = optVp.get();
        }

        return new AddToHistoryResult(
                returnVp.getUserId(),
                returnVp.getPostId(),
                command.viewedAt()
        );
    }
}
