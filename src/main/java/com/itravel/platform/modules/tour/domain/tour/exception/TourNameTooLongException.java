package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class TourNameTooLongException extends DomainException {
    public TourNameTooLongException() {
        super(DomainErrorCode.TOUR_NAME_TOO_LONG);
    }
}

