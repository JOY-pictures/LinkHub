package com.linkhub.linkhub.history.infra;

import com.linkhub.linkhub.history.domain.ViewedPost;
import com.linkhub.linkhub.history.domain.ViewedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ViewedPostJpaAdapter implements ViewedPostRepository {

    private final SpringDataViewedPostJpaRepository jpa;

    @Override
    public ViewedPost save(ViewedPost vp) {
        ViewedPostEntity vpe = toEntity(vp);
        ViewedPostEntity saved = jpa.save(vpe);
        return toDomain(saved);
    }

    @Override
    public Optional<ViewedPost> findByUserIdAndPostId(Long userId, Long postId) {
        return jpa.findByUserIdAndPostId(userId, postId)
                .map(this::toDomain);
    }

    private ViewedPostEntity toEntity(ViewedPost vp) {
        return new ViewedPostEntity(
                vp.getId(),
                vp.getUserId(),
                vp.getPostId(),
                vp.getViewedAt()
        );
    }

    private ViewedPost toDomain(ViewedPostEntity vpe) {
        return ViewedPost.reconstitute(
                vpe.getId(),
                vpe.getUserId(),
                vpe.getPostId(),
                vpe.getViewedAt()
        );
    }
}
