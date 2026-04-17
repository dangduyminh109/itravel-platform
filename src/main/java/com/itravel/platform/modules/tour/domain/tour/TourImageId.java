package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidTourImageIdException;

import java.util.Objects;

public record TourImageId(Long value) {
    public TourImageId {
        if (Objects.isNull(value)) {
            throw new InvalidTourImageIdException();
        }
    }
}

