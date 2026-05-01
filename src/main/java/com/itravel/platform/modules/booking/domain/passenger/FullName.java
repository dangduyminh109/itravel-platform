package com.itravel.platform.modules.booking.domain.passenger;

import com.itravel.platform.modules.booking.domain.exception.InvalidPassengerFullNameException;

public record FullName(String value) {
    public FullName {
        if (value == null || value.isBlank()) {
            throw new InvalidPassengerFullNameException();
        }
    }
}
