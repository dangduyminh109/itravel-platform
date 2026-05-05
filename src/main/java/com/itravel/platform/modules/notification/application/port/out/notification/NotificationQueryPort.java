package com.itravel.platform.modules.notification.application.port.out.notification;

import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificationQueryPort {
    Optional<NotificationDTO> findById(String id);
    Page<NotificationDTO> findAllByRecipientId(String recipientId, Pageable pageable);
    long countUnreadByRecipientId(String recipientId);
}
