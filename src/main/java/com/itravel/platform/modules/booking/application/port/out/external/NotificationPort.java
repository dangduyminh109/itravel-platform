package com.itravel.platform.modules.booking.application.port.out.external;

public interface NotificationPort {
    void sendBookingConfirmation(String bookingId, String email);
}
