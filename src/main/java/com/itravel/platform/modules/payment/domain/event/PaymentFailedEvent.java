package com.itravel.platform.modules.payment.domain.event;

import java.time.Instant;

public record PaymentFailedEvent(
        String paymentId,
        String referenceCode,
        String reason,
        Instant failedAt
) {
}
