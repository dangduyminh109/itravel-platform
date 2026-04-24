package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidQuantityException extends DomainException {
    public InvalidQuantityException() {
        super(DomainErrorCode.DUPLICATE_ITINERARY_DAY);
    }
}