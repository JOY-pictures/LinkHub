package com.linkhub.linkhub.content.application.usecase;

import com.linkhub.linkhub.content.application.dto.PostView;
import com.linkhub.linkhub.content.domain.PostContent;
import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.content.domain.TextContent;
import com.linkhub.linkhub.modes.application.port.ModeInformationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetPostByIdUseCase {
    private final PostRepository postRepository;
    private final ModeInformationPort modeInformationPort;


    public Optional<PostView> getById (Long postId) {

        return postRepository.findById(postId).
                map(post -> {
                    String text = extractText(post.getContent());
                    String modeName = modeInformationPort.findModeById(post.getModeId()).modeName();
                    return new PostView(
                            post.getId(),
                            post.getAuthorId(),
                            text,
                            post.getCreatedAt().toString(),
                            modeName
                    );
                });
    }

    private String extractText(PostContent content) {
        if (content instanceof TextContent tc) {
            return tc.getText();
        }
        throw new IllegalArgumentException("Unsupported content type: " + content.getClass());
    }

}
