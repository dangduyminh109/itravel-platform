package com.itravel.platform.modules.booking.application.dto;

import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;

@Builder
public record BookingListItemDTO(
        String id,
        String bookingCode,
        ContactInfoDTO contactInfo,
        BookingStatus status,
        BigDecimal totalAmount,
        Instant createdAt
) {}
