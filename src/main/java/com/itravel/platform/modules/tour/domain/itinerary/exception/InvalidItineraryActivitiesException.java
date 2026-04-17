package com.itravel.platform.modules.tour.domain.itinerary.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidItineraryActivitiesException extends DomainException {
    public InvalidItineraryActivitiesException() {
        super(DomainErrorCode.INVALID_ITINERARY_ACTIVITIES);
    }
}

