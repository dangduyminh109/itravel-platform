package com.itravel.platform.modules.payment.infrastructure.persistence.repository;

import com.itravel.platform.modules.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, String> {
    Optional<PaymentJpaEntity> findByReferenceCode(String referenceCode);
}
