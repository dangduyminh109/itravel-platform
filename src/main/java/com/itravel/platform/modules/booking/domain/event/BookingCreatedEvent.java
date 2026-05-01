package com.itravel.platform.modules.booking.domain.event;

import java.time.Instant;

public record BookingCreatedEvent(
        String bookingId,
        String bookingCode,
        Instant createdAt
) {
}
