package com.itravel.platform.modules.notification.domain.event;

import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import com.itravel.platform.modules.notification.domain.notification.NotificationType;

import java.time.Instant;
import java.util.Set;

public record NotificationCreatedEvent(
    String notificationId,
    String recipientId,
    String title,
    String content,
    NotificationType type,
    Set<NotificationChannel> channels,
    Instant createdAt
) {
}
