package com.itravel.platform.modules.booking.application.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class BookingNotFoundException extends DomainException {
    public BookingNotFoundException() {
        super(DomainErrorCode.BOOKING_NOT_FOUND);
    }
}
