package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidAvailableSeatsException;

import java.util.Objects;

public record AvailableSeats(Integer value) {
    public AvailableSeats {
        if (Objects.isNull(value) || value < 0) {
            throw new InvalidAvailableSeatsException();
        }
    }
}

