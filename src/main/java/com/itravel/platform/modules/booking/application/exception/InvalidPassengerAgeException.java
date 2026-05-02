package com.itravel.platform.modules.booking.application.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPassengerAgeException extends DomainException {
    public InvalidPassengerAgeException() {
        super(DomainErrorCode.INVALID_PASSENGER_AGE);
    }
}
