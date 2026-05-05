package com.itravel.platform.modules.notification.infrastructure.adapter;

import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import com.itravel.platform.modules.notification.application.port.out.notification.NotificationQueryPort;
import com.itravel.platform.modules.notification.infrastructure.persistence.mapper.NotificationMapper;
import com.itravel.platform.modules.notification.infrastructure.persistence.repository.NotificationJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationQueryRepositoryImpl implements NotificationQueryPort {
    NotificationJpaRepository jpaRepository;

    @Override
    public Optional<NotificationDTO> findById(String id) {
        return jpaRepository.findById(id).map(NotificationMapper::toDTO);
    }

    @Override
    public Page<NotificationDTO> findAllByRecipientId(String recipientId, Pageable pageable) {
        return jpaRepository.findAllByRecipientId(recipientId, pageable).map(NotificationMapper::toDTO);
    }

    @Override
    public long countUnreadByRecipientId(String recipientId) {
        return jpaRepository.countByRecipientIdAndIsReadFalse(recipientId);
    }
}
