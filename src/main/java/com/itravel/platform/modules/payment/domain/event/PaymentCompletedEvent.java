package com.itravel.platform.modules.payment.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentCompletedEvent(
        String paymentId,
        String referenceCode,
        BigDecimal totalAmount,
        String gatewayTransactionId,
        Instant completedAt
) {
}
