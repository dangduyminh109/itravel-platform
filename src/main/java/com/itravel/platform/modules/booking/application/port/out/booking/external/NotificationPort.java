package com.itravel.platform.modules.booking.application.port.out.booking.external;

public interface NotificationPort {
    void sendBookingConfirmation(String bookingId, String email);
}
