package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidBookingStateTransitionException extends DomainException {
    public InvalidBookingStateTransitionException() {
        super(DomainErrorCode.BOOKING_INVALID_STATE_TRANSITION);
    }
}
