package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidItineraryDayNumberException;

import java.util.Objects;

public record ItineraryDayNumber(Integer value) {
    public ItineraryDayNumber {
        if (Objects.isNull(value) || value < 1) {
            throw new InvalidItineraryDayNumberException();
        }
    }
}

