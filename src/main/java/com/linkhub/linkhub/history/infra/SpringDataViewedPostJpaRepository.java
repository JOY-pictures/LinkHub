package com.linkhub.linkhub.history.infra;

import com.linkhub.linkhub.history.domain.ViewedPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataViewedPostJpaRepository extends JpaRepository<ViewedPostEntity, Long> {
    Optional<ViewedPostEntity> findByUserIdAndPostId(Long userId, Long postId);

    @Query("SELECT v.postId FROM ViewedPostEntity v WHERE v.userId = :userId ORDER BY v.viewedAt DESC")
    List<Long> findPostIdsByUserId(Long userId, Pageable pageable);

    @Query("SELECT v FROM ViewedPostEntity v WHERE v.userId = :userId ORDER BY v.viewedAt DESC")
    List<ViewedPost> findPostsByUserId(Long userId, Pageable pageable);
}

