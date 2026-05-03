package com.itravel.platform.modules.booking.api.dto.response;

import com.itravel.platform.modules.booking.application.dto.ContactInfoDTO;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record BookingResponse(
        String id,
        String bookingCode,
        String customerId,
        ContactInfoDTO contactInfo,
        String note,
        String status,
        BigDecimal totalAmount,
        String currency,
        Instant expiredAt,
        Instant createdAt,
        List<BookingItemResponse> items,
        List<PassengerResponse> passengers
) {
    public record BookingItemResponse(
            String id,
            String serviceType,
            String referenceId,
            List<PriceLineResponse> priceBreakdown
    ) {}

    public record PriceLineResponse(
            String type,
            String name,
            BigDecimal unitPrice,
            String currency,
            int quantity,
            BigDecimal total
    ) {}

    public record PassengerResponse(
            String id,
            String fullName,
            String dateOfBirth,
            String gender,
            String identityNumber,
            String passengerType
    ) {}
}
