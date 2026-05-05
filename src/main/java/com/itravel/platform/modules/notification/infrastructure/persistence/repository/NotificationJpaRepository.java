package com.itravel.platform.modules.notification.infrastructure.persistence.repository;

import com.itravel.platform.modules.notification.infrastructure.persistence.entity.NotificationJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationJpaEntity, String> {
    List<NotificationJpaEntity> findAllByRecipientId(String recipientId);
    Page<NotificationJpaEntity> findAllByRecipientId(String recipientId, Pageable pageable);
    long countByRecipientIdAndIsReadFalse(String recipientId);
}
