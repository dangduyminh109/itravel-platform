package com.itravel.platform.modules.notification.application.port.in.notification;

import com.itravel.platform.modules.notification.domain.notification.NotificationId;

public interface MarkNotificationAsReadUseCase {
    void markAsRead(NotificationId id);
    void markAllAsRead(String recipientId);
}
