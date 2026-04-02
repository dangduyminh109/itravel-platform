package com.itravel.platform.modules.tour.domain.itinerary;

import com.itravel.platform.modules.tour.domain.itinerary.exception.InvalidItineraryTitleException;
import com.itravel.platform.modules.tour.domain.itinerary.exception.ItineraryTitleTooLongException;

import java.util.Objects;

public record ItineraryTitle(String value) {
    public ItineraryTitle {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new InvalidItineraryTitleException();
        }
        if (value.length() > 200) {
            throw new ItineraryTitleTooLongException();
        }
    }
}

