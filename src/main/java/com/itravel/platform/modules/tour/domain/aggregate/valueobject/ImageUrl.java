package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidTourImageUrlException;

public record ImageUrl(String value) {
    public ImageUrl {
        if (value == null || value.isBlank()) {
            throw new InvalidTourImageUrlException();
        }
    }
}

