package com.itravel.platform.modules.notification.application.event.handler.notification;

import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.port.in.customer.facade.CustomerQueryFacade;
import com.itravel.platform.modules.identity.application.port.in.user.facade.UserQueryFacade;
import com.itravel.platform.modules.identity.application.query.accountlink.GetAccountLinkHandler;
import com.itravel.platform.modules.notification.application.port.out.notification.NotificationEmailPort;
import com.itravel.platform.modules.notification.domain.event.NotificationCreatedEvent;
import com.itravel.platform.modules.notification.domain.notification.NotificationChannel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationCreatedEventHandler {
    NotificationEmailPort notificationEmailPort;
    UserQueryFacade userQueryFacade;
    CustomerQueryFacade customerQueryFacade;
    GetAccountLinkHandler getAccountLinkHandler;

    @Async
    @EventListener
    public void handle(NotificationCreatedEvent event) {
        log.info("Handling NotificationCreatedEvent for notification id: {}", event.notificationId());

        if (event.channels().contains(NotificationChannel.EMAIL)) {
            try {
                AccountLinkDTO accountLink = getAccountLinkHandler.getByAccountId(event.recipientId());
                String email = null;
                String name = null;

                if ("SYSTEM_USER".equals(accountLink.targetType())) {
                    UserDetailDTO user = userQueryFacade.getUser(accountLink.targetId());
                    if (user != null) {
                        email = user.email();
                        name = user.fullName();
                    }
                } else if ("CUSTOMER".equals(accountLink.targetType())) {
                    CustomerDetailDTO customer = customerQueryFacade.getCustomer(accountLink.targetId());
                    if (customer != null) {
                        email = customer.email();
                        name = customer.fullName();
                    }
                }

                if (email != null) {
                    log.info("Sending email notification to: {}", email);
                    notificationEmailPort.sendNotificationEmail(
                        email,
                        name != null ? name : "Quý khách",
                        event.title(),
                        event.content()
                    );
                } else {
                    log.warn("Cannot send email: email not found for recipientId: {} (Target: {})", 
                        event.recipientId(), accountLink.targetType());
                }
            } catch (Exception e) {
                log.error("Error resolving recipient info for email notification: {}", event.recipientId(), e);
            }
        }
    }
}
