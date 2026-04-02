package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidAvailableSeatsException;

import java.util.Objects;

public record AvailableSeats(Integer value) {
    public AvailableSeats {
        if (Objects.isNull(value) || value < 0) {
            throw new InvalidAvailableSeatsException();
        }
    }
}

