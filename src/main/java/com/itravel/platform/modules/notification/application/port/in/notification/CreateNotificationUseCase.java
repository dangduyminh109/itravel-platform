package com.itravel.platform.modules.notification.application.port.in.notification;

import com.itravel.platform.modules.notification.application.command.model.CreateNotificationCommand;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;

public interface CreateNotificationUseCase {
    NotificationId createNotification(CreateNotificationCommand command);
}
