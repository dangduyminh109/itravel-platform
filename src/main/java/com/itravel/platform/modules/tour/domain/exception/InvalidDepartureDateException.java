package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidDepartureDateException extends DomainException {
    public InvalidDepartureDateException() {
        super(DomainErrorCode.INVALID_DEPARTURE_DATE);
    }
}

