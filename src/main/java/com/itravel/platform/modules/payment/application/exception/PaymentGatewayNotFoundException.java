package com.itravel.platform.modules.payment.application.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class PaymentGatewayNotFoundException extends DomainException {
    public PaymentGatewayNotFoundException() {
        super(DomainErrorCode.PAYMENT_GATEWAY_NOT_FOUND);
    }
}
