package com.itravel.platform.modules.booking.domain.event;

import java.time.Instant;

public record BookingExpiredEvent(
        String bookingId,
        String bookingCode,
        Instant expiredAt
) {
}
