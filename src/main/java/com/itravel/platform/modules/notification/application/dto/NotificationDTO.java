package com.itravel.platform.modules.notification.application.dto;

import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import com.itravel.platform.modules.notification.domain.notification.NotificationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDTO {
    String id;
    String recipientId;
    String title;
    String content;
    NotificationType type;
    boolean isRead;
    String url;
    Set<NotificationChannel> channels;
    Instant createdAt;
}
