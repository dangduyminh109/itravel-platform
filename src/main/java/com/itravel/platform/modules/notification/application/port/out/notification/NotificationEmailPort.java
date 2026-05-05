package com.itravel.platform.modules.notification.application.port.out.notification;

public interface NotificationEmailPort {
    void sendNotificationEmail(String to, String recipientName, String title, String content);
}
