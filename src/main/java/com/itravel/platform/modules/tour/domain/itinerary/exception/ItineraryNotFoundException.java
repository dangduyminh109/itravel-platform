package com.itravel.platform.modules.tour.domain.itinerary.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ItineraryNotFoundException extends DomainException {
    public ItineraryNotFoundException() {
        super(DomainErrorCode.ITINERARY_NOT_FOUND);
    }
}