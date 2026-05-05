package com.itravel.platform.modules.notification.application.port.out.notification;

import com.itravel.platform.modules.notification.domain.notification.Notification;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;

import java.util.List;
import java.util.Optional;

public interface NotificationRepositoryPort {
    void save(Notification notification);
    Optional<Notification> findById(NotificationId id);
    List<Notification> findAllByRecipientId(String recipientId);
    long countUnreadByRecipientId(String recipientId);
    void markAllAsRead(String recipientId);
}
