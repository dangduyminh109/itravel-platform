package com.itravel.platform.modules.booking.domain.bookingItem;

import com.itravel.platform.modules.booking.domain.exception.InvalidBookingItemIdException;

import java.util.UUID;

public record BookingItemId(String value) {
    public BookingItemId {
        if (value == null || value.isBlank()) {
            throw new InvalidBookingItemIdException();
        }
    }

    public static BookingItemId generate() {
        return new BookingItemId(UUID.randomUUID().toString());
    }
}
