package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class PriceBreakdownRequiredException extends DomainException {
    public PriceBreakdownRequiredException() {
        super(DomainErrorCode.PRICE_BREAKDOWN_REQUIRED);
    }
}
