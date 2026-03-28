package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidItineraryDayNumberException extends DomainException {
    public InvalidItineraryDayNumberException() {
        super(DomainErrorCode.INVALID_ITINERARY_DAY_NUMBER);
    }
}

