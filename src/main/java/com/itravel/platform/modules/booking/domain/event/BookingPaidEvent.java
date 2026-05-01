package com.itravel.platform.modules.booking.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

public record BookingPaidEvent(
        String bookingId,
        String bookingCode,
        BigDecimal totalAmount,
        Instant paidAt
) {
}
