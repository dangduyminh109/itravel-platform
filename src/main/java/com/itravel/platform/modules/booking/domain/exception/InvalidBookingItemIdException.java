package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidBookingItemIdException extends DomainException {
    public InvalidBookingItemIdException() {
        super(DomainErrorCode.INVALID_BOOKING_ID);
    }
}
