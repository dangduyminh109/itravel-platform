package com.itravel.platform.common.domain.aggregate.valueobject.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class MoneyCurrencyCannotBeNullException extends DomainException {
    public MoneyCurrencyCannotBeNullException() {
        super(DomainErrorCode.MONEY_CURRENCY_CANNOT_BE_NULL);
    }
}
