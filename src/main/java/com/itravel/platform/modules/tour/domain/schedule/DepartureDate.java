package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidDepartureDateException;

import java.time.LocalDateTime;
import java.util.Objects;

public record DepartureDate(LocalDateTime value) {
    public DepartureDate {
        if (Objects.isNull(value)) {
            throw new InvalidDepartureDateException();
        }
    }
}

