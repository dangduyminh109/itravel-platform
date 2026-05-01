package com.itravel.platform.modules.booking.domain.passenger;

import com.itravel.platform.modules.booking.domain.exception.InvalidPassengerIdException;

import java.util.UUID;

public record PassengerId(String value) {
    public PassengerId {
        if (value == null || value.isBlank()) {
            throw new InvalidPassengerIdException();
        }
    }

    public static PassengerId generate() {
        return new PassengerId(UUID.randomUUID().toString());
    }
}
