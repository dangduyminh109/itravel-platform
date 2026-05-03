package com.itravel.platform.modules.payment.application.command.model;

import com.itravel.platform.modules.payment.domain.payment.PaymentMethod;

public record CreatePaymentCommand(
        String bookingCode,
        PaymentMethod paymentMethod,
        String clientId
) {
}
