package com.itravel.platform.modules.booking.domain.bookingItem;

import java.time.LocalDate;

public record TourSnapshot(
        String tourName,
        LocalDate startDate,
        LocalDate endDate,
        String departureLocation
) implements ServiceSnapshot {
}
