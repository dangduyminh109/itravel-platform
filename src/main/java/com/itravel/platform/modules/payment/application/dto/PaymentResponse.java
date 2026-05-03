package com.itravel.platform.modules.payment.application.dto;

import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Builder;

@Builder
public record PaymentResponse(
        String id,
        String referenceCode,
        BigDecimal totalAmount,
        String currency,
        String paymentMethod,
        PaymentStatus status,
        String gatewayTransactionId,
        String failureReason,
        Instant createdAt,
        Instant updatedAt
) {
}
