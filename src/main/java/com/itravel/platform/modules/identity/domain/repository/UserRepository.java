package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.application.query.UserGeneralInfo;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(UserId id);
    List<User> getUsers();
    UserGeneralInfo getUserGeneralInfo();
    Page<User> getUsers(String keyword, Pageable pageable, boolean isDeleted);
    void save(User user);
    void destroy(UserId id);
}
