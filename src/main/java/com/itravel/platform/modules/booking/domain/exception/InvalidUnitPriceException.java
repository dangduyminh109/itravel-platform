package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidUnitPriceException extends DomainException {
    public InvalidUnitPriceException() {
        super(DomainErrorCode.INVALID_UNIT_PRICE);
    }
}
