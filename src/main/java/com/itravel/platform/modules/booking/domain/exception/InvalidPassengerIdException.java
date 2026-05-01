package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPassengerIdException extends DomainException {
    public InvalidPassengerIdException() {
        super(DomainErrorCode.INVALID_BOOKING_ID);
    }
}
