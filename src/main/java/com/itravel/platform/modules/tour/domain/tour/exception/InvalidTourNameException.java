package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidTourNameException extends DomainException {
    public InvalidTourNameException() {
        super(DomainErrorCode.INVALID_TOUR_NAME);
    }
}

