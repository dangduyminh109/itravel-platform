package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class DuplicateItineraryDayException extends DomainException {
    public DuplicateItineraryDayException() {
        super(DomainErrorCode.DUPLICATE_ITINERARY_DAY);
    }
}