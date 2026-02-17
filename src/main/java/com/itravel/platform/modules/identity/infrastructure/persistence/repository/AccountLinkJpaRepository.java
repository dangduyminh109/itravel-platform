package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLinkJpaRepository extends JpaRepository<AccountLinkJpaEntity,Long> {
    AccountLinkJpaEntity findByAccountId(String id);
    AccountLinkJpaEntity findByTargetId(String id);
}
