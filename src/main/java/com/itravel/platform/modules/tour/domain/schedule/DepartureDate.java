package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidDepartureDateException;

import java.time.LocalDate;
import java.util.Objects;

public record DepartureDate(LocalDate value) {
    public DepartureDate {
        if (Objects.isNull(value)) {
            throw new InvalidDepartureDateException();
        }
    }
}

