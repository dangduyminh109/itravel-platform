package com.itravel.platform.common.domain.aggregate.valueobject.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class MoneyAmountCannotBeNullException extends DomainException {
    public MoneyAmountCannotBeNullException() {
        super(DomainErrorCode.MONEY_AMOUNT_CANNOT_BE_NULL);
    }
}
