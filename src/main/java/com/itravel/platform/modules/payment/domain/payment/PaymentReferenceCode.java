package com.itravel.platform.modules.payment.domain.payment;

import com.itravel.platform.modules.payment.domain.exception.InvalidPaymentReferenceCodeException;

public record PaymentReferenceCode(String value) {
    public PaymentReferenceCode {
        if (value == null || value.isBlank()) {
            throw new InvalidPaymentReferenceCodeException();
        }
    }
}
