package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.user.User;
import com.itravel.platform.modules.identity.domain.user.UserId;
import com.itravel.platform.modules.identity.application.port.out.user.UserRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.UserMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.UserJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryImpl implements UserRepository {
    UserJpaRepository repository;
    UserMapper userMapper;

    @Override
    public Optional<User> findById(UserId id) {
        return repository
                .findById(id.value())
                .map(UserMapper::toUserDomain);
    }

    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toUserJpaEntity(user);
        repository.save(userJpaEntity);
    }

    @Override
    public void destroy(UserId id) {
        repository.findById(id.value())
                .ifPresent(repository::delete);
    }
}
