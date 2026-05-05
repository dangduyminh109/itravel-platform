package com.itravel.platform.modules.notification.domain.notification;

import java.util.UUID;

public record NotificationId(String value) {
    public static NotificationId generate() {
        return new NotificationId(UUID.randomUUID().toString());
    }
}
