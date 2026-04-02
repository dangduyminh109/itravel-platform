package com.itravel.platform.modules.tour.domain.tourImage;

import com.itravel.platform.modules.tour.domain.tourImage.exception.InvalidTourImageUrlException;

public record ImageUrl(String value) {
    public ImageUrl {
        if (value == null || value.isBlank()) {
            throw new InvalidTourImageUrlException();
        }
    }
}

