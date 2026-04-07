package com.linkhub.linkhub.content.infra;

import com.linkhub.linkhub.content.domain.Post;
import com.linkhub.linkhub.content.domain.PostRepository;
import com.linkhub.linkhub.content.domain.PostType;
import com.linkhub.linkhub.content.domain.TextContent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryJpaAdapter implements PostRepository {
    private final SpringDataPostJpaRepository jpa;

    @Override
    public Post save(Post post) {
        PostJpaEntity entity = toJpaEntity(post);
        PostJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }


    private PostJpaEntity toJpaEntity (Post post) {
        if (post.getContent() instanceof TextContent tc) {
            return new TextPostJpaEntity(
                    post.getAuthorId(),
                    post.getCreatedAt(),
                    tc.getText()
            );
        }
        throw new IllegalArgumentException("Unknown content type: "+ post.getContent().getClass());
    }

    private Post toDomain(PostJpaEntity entity) {
        if (entity instanceof TextPostJpaEntity tp) {
            TextContent content = TextContent.create(tp.getText());
            return Post.reconstitute(
                    entity.getId(),
                    entity.getAuthorId(),
                    PostType.TEXT,
                    entity.getCreatedAt(),
                    content
            );
        }
        throw new IllegalArgumentException("Unknown entity type: "+ entity.getClass());
    }
}
