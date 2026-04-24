package com.linkhub.linkhub.content.application.service;

import com.linkhub.linkhub.content.application.model.PostSummary;
import com.linkhub.linkhub.content.application.port.PostSortingPort;
import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSortingService implements PostSortingPort {

    private final PostRepository postRepository;
    private final ModeInformationPort modeInformationPort;

    @Override
    public List<PostSummary> findPostsByModeWithLimit(String modeName, int limit) {
        Long modeId = modeInformationPort.findModeIdByName(modeName);
        return postRepository.findPostsByModeIdWithLimit(modeId, limit)
                .stream()
                .map(post -> {
                    return new PostSummary(
                            post.getId(),
                            post.getAuthorId(),
                            post.getContent(),
                            post.getCreatedAt()
                    );
                }).toList();
    }
}
