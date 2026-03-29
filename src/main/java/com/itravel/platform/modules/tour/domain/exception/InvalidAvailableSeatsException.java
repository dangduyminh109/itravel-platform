package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidAvailableSeatsException extends DomainException {
    public InvalidAvailableSeatsException() {
        super(DomainErrorCode.INVALID_AVAILABLE_SEATS);
    }
}

