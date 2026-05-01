package com.itravel.platform.modules.booking.domain.bookingItem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ServiceType {
    TOUR("T"),
    HOTEL("H"),
    FLIGHT("F");

    String prefix;

    ServiceType(String prefix) {
        this.prefix = prefix;
    }
}
