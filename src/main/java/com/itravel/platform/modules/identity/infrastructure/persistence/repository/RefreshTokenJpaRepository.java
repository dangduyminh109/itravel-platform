package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenJpaEntity,String> {
    RefreshTokenJpaEntity findByTokenHash(String token);
}
