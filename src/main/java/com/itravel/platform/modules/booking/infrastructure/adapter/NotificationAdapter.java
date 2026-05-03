package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.port.out.booking.external.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationAdapter implements NotificationPort {

    @Override
    public void sendBookingConfirmation(String bookingId, String email) {
        log.info("Mock sending booking confirmation to {} for booking {}", email, bookingId);
    }
}
