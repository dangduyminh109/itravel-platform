package com.itravel.platform.modules.notification.application.port.in.notification.facade;

import com.itravel.platform.modules.notification.application.command.model.CreateNotificationCommand;
import com.itravel.platform.modules.notification.application.port.in.notification.CreateNotificationUseCase;
import com.itravel.platform.modules.notification.application.port.in.notification.MarkNotificationAsReadUseCase;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationCommandFacade {
    CreateNotificationUseCase createNotificationUseCase;
    MarkNotificationAsReadUseCase markNotificationAsReadUseCase;

    public NotificationId createNotification(CreateNotificationCommand command) {
        return createNotificationUseCase.createNotification(command);
    }

    public void markAsRead(NotificationId id) {
        markNotificationAsReadUseCase.markAsRead(id);
    }

    public void markAllAsRead(String recipientId) {
        markNotificationAsReadUseCase.markAllAsRead(recipientId);
    }
}
