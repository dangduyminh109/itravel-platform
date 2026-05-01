package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPriceLineQuantityException extends DomainException {
    public InvalidPriceLineQuantityException() {
        super(DomainErrorCode.INVALID_PRICE_LINE_QUANTITY);
    }
}
