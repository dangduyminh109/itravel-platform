package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidItineraryIdException;

import java.util.Objects;

public record ItineraryId(Long value) {
    public ItineraryId {
        if (Objects.isNull(value)) {
            throw new InvalidItineraryIdException();
        }
    }
}

