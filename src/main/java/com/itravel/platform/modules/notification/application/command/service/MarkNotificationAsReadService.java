package com.itravel.platform.modules.notification.application.command.service;

import com.itravel.platform.modules.notification.application.port.in.notification.MarkNotificationAsReadUseCase;
import com.itravel.platform.modules.notification.application.port.out.notification.NotificationRepositoryPort;
import com.itravel.platform.modules.notification.domain.notification.Notification;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MarkNotificationAsReadService implements MarkNotificationAsReadUseCase {
    NotificationRepositoryPort notificationRepository;

    @Override
    @Transactional
    public void markAsRead(NotificationId id) {
        notificationRepository.findById(id).ifPresent(notification -> {
            notification.markAsRead();
            notificationRepository.save(notification);
        });
    }

    @Override
    @Transactional
    public void markAllAsRead(String recipientId) {
        List<Notification> notifications = notificationRepository.findAllByRecipientId(recipientId);
        notifications.forEach(notification -> {
            if (!notification.isRead()) {
                notification.markAsRead();
                notificationRepository.save(notification);
            }
        });
    }
}
