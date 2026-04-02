package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidDiscountPriceException extends DomainException {
    public InvalidDiscountPriceException() {
        super(DomainErrorCode.INVALID_DISCOUNT_PRICE);
    }
}

