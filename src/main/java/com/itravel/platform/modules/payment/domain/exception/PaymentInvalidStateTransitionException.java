package com.itravel.platform.modules.payment.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class PaymentInvalidStateTransitionException extends DomainException {
    public PaymentInvalidStateTransitionException() {
        super(DomainErrorCode.PAYMENT_INVALID_STATE_TRANSITION);
    }
}
