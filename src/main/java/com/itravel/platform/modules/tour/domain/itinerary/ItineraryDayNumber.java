package com.itravel.platform.modules.tour.domain.itinerary;

import com.itravel.platform.modules.tour.domain.itinerary.exception.InvalidItineraryDayNumberException;

import java.util.Objects;

public record ItineraryDayNumber(Integer value) {
    public ItineraryDayNumber {
        if (Objects.isNull(value) || value < 1) {
            throw new InvalidItineraryDayNumberException();
        }
    }
}

