package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidTourImageIdException;

import java.util.Objects;

public record TourImageId(Long value) {
    public TourImageId {
        if (Objects.isNull(value)) {
            throw new InvalidTourImageIdException();
        }
    }
}

