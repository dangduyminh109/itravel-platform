package com.itravel.platform.modules.booking.api.dto.response;

import com.itravel.platform.modules.booking.application.dto.ContactInfoDTO;
import java.math.BigDecimal;
import java.time.Instant;

public record BookingListItemResponse(
        String id,
        String bookingCode,
        ContactInfoDTO contactInfo,
        String status,
        BigDecimal totalAmount,
        Instant createdAt
) {}
