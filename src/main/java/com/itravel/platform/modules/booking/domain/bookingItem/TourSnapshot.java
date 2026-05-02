package com.itravel.platform.modules.booking.domain.bookingItem;

import java.time.LocalDate;

public record TourSnapshot(
        String tourName,
        LocalDate departureDate,
        String departureLocation,
        String destinationLocation
) implements ServiceSnapshot {
}
