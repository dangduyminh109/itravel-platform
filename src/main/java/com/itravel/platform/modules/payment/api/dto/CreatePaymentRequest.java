package com.itravel.platform.modules.payment.api.dto;

public record CreatePaymentRequest(
    String bookingCode,
    String paymentMethod
) {}
