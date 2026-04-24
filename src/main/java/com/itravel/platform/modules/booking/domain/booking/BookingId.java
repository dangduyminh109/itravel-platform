package com.itravel.platform.modules.booking.domain.booking;

import java.util.UUID;

public record BookingId(String value) {
    public static BookingId generate(){
        return new BookingId(UUID.randomUUID().toString());
    }
}
