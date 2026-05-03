package com.itravel.platform.modules.payment.application.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class PaymentNotFoundException extends DomainException {
    public PaymentNotFoundException() {
        super(DomainErrorCode.PAYMENT_NOT_FOUND);
    }
}
