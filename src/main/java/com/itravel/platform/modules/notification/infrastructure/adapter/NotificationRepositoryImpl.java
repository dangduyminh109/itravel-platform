package com.itravel.platform.modules.notification.infrastructure.adapter;

import com.itravel.platform.modules.notification.application.port.out.notification.NotificationRepositoryPort;
import com.itravel.platform.modules.notification.domain.notification.Notification;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;
import com.itravel.platform.modules.notification.infrastructure.persistence.entity.NotificationJpaEntity;
import com.itravel.platform.modules.notification.infrastructure.persistence.mapper.NotificationMapper;
import com.itravel.platform.modules.notification.infrastructure.persistence.repository.NotificationJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationRepositoryImpl implements NotificationRepositoryPort {
    NotificationJpaRepository jpaRepository;
    NotificationMapper notificationMapper;
    ApplicationEventPublisher eventPublisher;

    @Override
    public void save(Notification notification) {
        NotificationJpaEntity entity = notificationMapper.toJpaEntity(notification);
        jpaRepository.save(entity);
        notification.getDomainEvents().forEach(eventPublisher::publishEvent);
        notification.clearDomainEvents();
    }

    @Override
    public Optional<Notification> findById(NotificationId id) {
        return jpaRepository.findById(id.value()).map(NotificationMapper::toDomain);
    }

    @Override
    public List<Notification> findAllByRecipientId(String recipientId) {
        return jpaRepository.findAllByRecipientId(recipientId).stream()
                .map(NotificationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countUnreadByRecipientId(String recipientId) {
        return jpaRepository.countByRecipientIdAndIsReadFalse(recipientId);
    }
}
