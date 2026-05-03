package com.itravel.platform.modules.payment.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPaymentReferenceCodeException extends DomainException {
    public InvalidPaymentReferenceCodeException() {
        super(DomainErrorCode.INVALID_PAYMENT_REFERENCE_CODE);
    }
}
