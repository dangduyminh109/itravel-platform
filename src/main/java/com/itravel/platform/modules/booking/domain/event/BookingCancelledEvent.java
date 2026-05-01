package com.itravel.platform.modules.booking.domain.event;

import java.time.Instant;

public record BookingCancelledEvent(
        String bookingId,
        String bookingCode,
        Instant cancelledAt
) {
}
