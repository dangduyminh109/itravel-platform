package com.itravel.platform.modules.payment.domain.payment;

import java.util.UUID;

public record PaymentId(String value) {
    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID().toString());
    }
}
