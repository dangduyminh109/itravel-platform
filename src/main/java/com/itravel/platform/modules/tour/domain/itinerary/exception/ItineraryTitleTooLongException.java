package com.itravel.platform.modules.tour.domain.itinerary.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ItineraryTitleTooLongException extends DomainException {
    public ItineraryTitleTooLongException() {
        super(DomainErrorCode.ITINERARY_TITLE_TOO_LONG);
    }
}

