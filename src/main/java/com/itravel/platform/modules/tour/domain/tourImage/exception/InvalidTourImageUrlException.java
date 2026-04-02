package com.itravel.platform.modules.tour.domain.tourImage.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidTourImageUrlException extends DomainException {
    public InvalidTourImageUrlException() {
        super(DomainErrorCode.INVALID_TOUR_IMAGE_URL);
    }
}

