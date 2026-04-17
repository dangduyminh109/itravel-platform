package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidDays;
import com.itravel.platform.modules.tour.domain.tour.exception.InvalidDaysNightsRelationException;
import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidNights;

public record TourDuration(Integer days, Integer nights) {
    public TourDuration {
        if (days == null || days < 1) {
            throw new InvalidDays();
        }
        if (nights == null || nights < 0) {
            throw new InvalidNights();
        }
        if (Math.abs(days - nights) > 1) {
            throw new InvalidDaysNightsRelationException();
        }
    }
}
