package com.itravel.platform.modules.notification.application.command.service;

import com.itravel.platform.modules.notification.application.command.model.CreateNotificationCommand;
import com.itravel.platform.modules.notification.application.port.in.notification.CreateNotificationUseCase;
import com.itravel.platform.modules.notification.application.port.out.notification.NotificationRepositoryPort;
import com.itravel.platform.modules.notification.domain.notification.Notification;
import com.itravel.platform.modules.notification.domain.notification.NotificationId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateNotificationService implements CreateNotificationUseCase {
    NotificationRepositoryPort notificationRepository;

    @Override
    @Transactional
    public NotificationId createNotification(CreateNotificationCommand command) {
        Notification notification = Notification.create(
            command.recipientId(),
            command.title(),
            command.content(),
            command.type(),
            command.url(),
            command.channels()
        );

        notificationRepository.save(notification);

        return notification.getId();
    }
}
