package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidItineraryIdException extends DomainException {
    public InvalidItineraryIdException() {
        super(DomainErrorCode.INVALID_ITINERARY_ID);
    }
}

