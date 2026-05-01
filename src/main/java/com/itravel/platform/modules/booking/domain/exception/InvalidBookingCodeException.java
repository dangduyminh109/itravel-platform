package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidBookingCodeException extends DomainException {
    public InvalidBookingCodeException() {
        super(DomainErrorCode.INVALID_BOOKING_CODE);
    }
}
