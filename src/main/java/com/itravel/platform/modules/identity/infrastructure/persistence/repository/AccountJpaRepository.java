package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity,String> {
    Optional<AccountJpaEntity> findByUsername(String username);
    Optional<AccountJpaEntity> findByEmail(String email);
}
