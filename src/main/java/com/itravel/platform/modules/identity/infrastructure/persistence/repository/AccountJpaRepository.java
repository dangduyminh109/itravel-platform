package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountJpaEntity,String> {
    Optional<AccountJpaEntity> findByUsername(String username);
    Optional<AccountJpaEntity> findByEmail(String email);
    @Query("""
       SELECT a FROM AccountJpaEntity a
       WHERE a.username = :identifier
          OR a.email = :identifier
       """)
    Optional<AccountJpaEntity> findByIdentifier(@Param("identifier") String identifier);
}
