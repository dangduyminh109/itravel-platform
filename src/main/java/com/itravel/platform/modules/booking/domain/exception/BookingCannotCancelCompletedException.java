package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class BookingCannotCancelCompletedException extends DomainException {
    public BookingCannotCancelCompletedException() {
        super(DomainErrorCode.BOOKING_CANNOT_CANCEL_COMPLETED);
    }
}
