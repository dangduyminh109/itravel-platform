package com.itravel.platform.modules.payment.api.dto;

import lombok.Builder;

@Builder
public record PaymentUrlResponse(
    String paymentUrl,
    String transactionId
) {}
