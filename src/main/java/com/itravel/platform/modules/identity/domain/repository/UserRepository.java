package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(UserId userId);
    List<User> getUsers();
    void save(User user);
    void destroy(UserId userId);
}
