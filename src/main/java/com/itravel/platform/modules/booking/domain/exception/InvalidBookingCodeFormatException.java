package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidBookingCodeFormatException extends DomainException {
    public InvalidBookingCodeFormatException() {
        super(DomainErrorCode.INVALID_BOOKING_CODE_FORMAT);
    }
}
