package com.itravel.platform.modules.identity.application.port.out.user;

import com.itravel.platform.modules.identity.domain.user.User;
import com.itravel.platform.modules.identity.domain.user.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findById(UserId id);
    void save(User user);
    void destroy(UserId id);
}

