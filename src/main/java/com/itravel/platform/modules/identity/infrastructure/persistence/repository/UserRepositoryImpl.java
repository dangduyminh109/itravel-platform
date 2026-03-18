package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.application.query.UserGeneralInfo;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import com.itravel.platform.modules.identity.domain.repository.UserRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserRepositoryImpl implements UserRepository {
    UserJpaRepository userJpaRepository;
    UserMapper userMapper;

    @Override
    public UserGeneralInfo getUserGeneralInfo() {
        return userJpaRepository.getUserGeneralInfo();
    }

    @Override
    public Optional<User> findById(UserId id) {
        return userJpaRepository
                .findById(id.value())
                .map(UserMapper::toUserDomain);
    }

    @Override
    public List<User> getUsers() {
        return userJpaRepository
                .findAll()
                .stream()
                .map(UserMapper::toUserDomain)
                .toList();
    }

    @Override
    public Page<User> getUsers(String keyword, Pageable pageable,boolean isDeleted) {
        return userJpaRepository
                .getUsers(keyword, pageable,isDeleted)
                .map(UserMapper::toUserDomain);
    }

    @Override
    public void save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toUserJpaEntity(user);
        userJpaRepository.save(userJpaEntity);
    }

    @Override
    public void destroy(UserId id) {
        userJpaRepository.findById(id.value())
                .ifPresent(userJpaRepository::delete);
    }
}
