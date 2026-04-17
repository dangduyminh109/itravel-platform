package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidTourImageIdException extends DomainException {
    public InvalidTourImageIdException() {
        super(DomainErrorCode.INVALID_TOUR_IMAGE_ID);
    }
}

