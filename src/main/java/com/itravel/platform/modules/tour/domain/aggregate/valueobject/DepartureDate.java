package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidDepartureDateException;

import java.time.LocalDate;
import java.util.Objects;

public record DepartureDate(LocalDate value) {
    public DepartureDate {
        if (Objects.isNull(value)) {
            throw new InvalidDepartureDateException();
        }
    }
}

