package com.itravel.platform.modules.notification.application.port.in.notification.facade;

import com.itravel.platform.modules.notification.application.dto.NotificationDTO;
import com.itravel.platform.modules.notification.application.query.notification.GetNotificationsHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationQueryFacade {

    GetNotificationsHandler getNotificationsHandler;

    public Page<NotificationDTO> getNotificationsByRecipient(String recipientId, Pageable pageable) {
        return getNotificationsHandler.execute(recipientId, pageable);
    }

    public long getUnreadCount(String recipientId) {
        return getNotificationsHandler.getUnreadCount(recipientId);
    }
}
