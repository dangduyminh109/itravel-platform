package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidOriginPriceException extends DomainException {
    public InvalidOriginPriceException() {
        super(DomainErrorCode.INVALID_ORIGIN_PRICE);
    }
}

