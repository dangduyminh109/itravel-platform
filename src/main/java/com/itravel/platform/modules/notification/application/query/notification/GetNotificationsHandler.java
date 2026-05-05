package com.itravel.platform.modules.notification.application.query.notification;

import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import com.itravel.platform.modules.notification.application.port.out.notification.NotificationQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetNotificationsHandler {
    NotificationQueryPort notificationQueryPort;

    public Page<NotificationDTO> execute(String recipientId, Pageable pageable) {
        return notificationQueryPort.findAllByRecipientId(recipientId, pageable);
    }

    public long getUnreadCount(String recipientId) {
        return notificationQueryPort.countUnreadByRecipientId(recipientId);
    }
}
