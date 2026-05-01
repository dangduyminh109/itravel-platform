package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPassengerFullNameException extends DomainException {
    public InvalidPassengerFullNameException() {
        super(DomainErrorCode.INVALID_PASSENGER_NAME);
    }
}
