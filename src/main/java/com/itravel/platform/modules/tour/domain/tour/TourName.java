package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidTourNameException;
import com.itravel.platform.modules.tour.domain.tour.exception.TourNameTooLongException;

import java.util.Objects;

public record TourName(String value) {
    public TourName {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new InvalidTourNameException();
        }
        if (value.length() > 200) {
            throw new TourNameTooLongException();
        }
    }
}
