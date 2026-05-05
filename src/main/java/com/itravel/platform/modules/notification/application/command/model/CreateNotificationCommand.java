package com.itravel.platform.modules.notification.application.command.model;

import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import com.itravel.platform.modules.notification.domain.notification.NotificationType;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateNotificationCommand(
    String recipientId,
    String title,
    String content,
    NotificationType type,
    String url,
    Set<NotificationChannel> channels
) {
}
