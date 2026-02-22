package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountLinkJpaRepository extends JpaRepository<AccountLinkJpaEntity,Long> {
    Optional<AccountLinkJpaEntity> findByAccountId(String id);
    List<AccountLinkJpaEntity> findAllByAccountId(String id);
    Optional<AccountLinkJpaEntity> findByTargetId(String id);
}
