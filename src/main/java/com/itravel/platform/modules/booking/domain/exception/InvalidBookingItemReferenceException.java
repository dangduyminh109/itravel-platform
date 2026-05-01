package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidBookingItemReferenceException extends DomainException {
    public InvalidBookingItemReferenceException() {
        super(DomainErrorCode.INVALID_BOOKING_ITEM_REFERENCE);
    }
}
