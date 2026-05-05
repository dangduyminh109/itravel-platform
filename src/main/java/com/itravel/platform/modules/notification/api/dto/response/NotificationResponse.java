package com.itravel.platform.modules.notification.api.dto.response;

import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import com.itravel.platform.modules.notification.domain.notification.NotificationType;
import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record NotificationResponse(
    String id,
    String recipientId,
    String title,
    String content,
    NotificationType type,
    boolean isRead,
    String url,
    Set<NotificationChannel> channels,
    Instant createdAt
) {
}
