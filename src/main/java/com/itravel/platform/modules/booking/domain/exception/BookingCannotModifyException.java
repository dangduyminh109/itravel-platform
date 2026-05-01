package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class BookingCannotModifyException extends DomainException {
    public BookingCannotModifyException() {
        super(DomainErrorCode.BOOKING_CANNOT_MODIFY);
    }
}
