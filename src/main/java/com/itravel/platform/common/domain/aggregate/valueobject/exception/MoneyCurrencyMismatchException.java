package com.itravel.platform.common.domain.aggregate.valueobject.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class MoneyCurrencyMismatchException extends DomainException {
    public MoneyCurrencyMismatchException() {
        super(DomainErrorCode.MONEY_CURRENCY_MISMATCH);
    }
}
