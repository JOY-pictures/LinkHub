package com.linkhub.linkhub.users.infra;

import com.linkhub.linkhub.users.domain.User;
import com.linkhub.linkhub.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {

    private final SpringDataUserJpaRepository jpa;

    @Override
    public User save(User user) {
        UserJpaEntity entity = toJpaEntity(user);
        UserJpaEntity saved = jpa.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpa.findByUsername(username).map(this::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpa.existsByUsername(username);
    }

    private User toDomain(UserJpaEntity entity) {
        return User.reconstitute(
                entity.getId(),
                entity.getUsername(),
                entity.getDisplayName(),
                entity.getCreatedAt());
    }

    private UserJpaEntity toJpaEntity(User user) {
        if (user.getId() != null) {
            return new UserJpaEntity(user.getId(), user.getUsername(), user.getDisplayName(), user.getCreatedAt());
        }
        return new UserJpaEntity(user.getUsername(), user.getDisplayName(), user.getCreatedAt());
    }
}
