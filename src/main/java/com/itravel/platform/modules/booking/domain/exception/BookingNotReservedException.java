package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class BookingNotReservedException extends DomainException {
    public BookingNotReservedException() {
        super(DomainErrorCode.BOOKING_NOT_RESERVED);
    }
}

