package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPriceLineNameException extends DomainException {
    public InvalidPriceLineNameException() {
        super(DomainErrorCode.INVALID_PRICE_LINE_NAME);
    }
}
