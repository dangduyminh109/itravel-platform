package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.infrastructure.persistence.entity.OtpJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface OtpJpaRepository extends JpaRepository<OtpJpaEntity,Long> {
    Optional<OtpJpaEntity> findByEmailAndCode(String email, String otpCode);
    Optional<OtpJpaEntity> findFirstByEmailAndExpiresAtAfter(String email, Instant currentTime);
}
