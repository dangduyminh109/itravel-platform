package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainException;
import com.itravel.platform.common.exceptions.DomainErrorCode;

public class MissingAdultPriceException extends DomainException {
    public MissingAdultPriceException() {
        super(DomainErrorCode.MISSING_ADULT_PRICE);
    }
}
