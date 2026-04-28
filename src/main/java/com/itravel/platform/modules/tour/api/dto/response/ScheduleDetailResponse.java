package com.itravel.platform.modules.tour.api.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record ScheduleDetailResponse(
        Long id,
        LocalDateTime departureDate,
        BigDecimal surcharge,
        Integer availableSeats,
        Integer totalSeats,
        Integer bookedSeats,
        Integer lockedSeats,
        PricingResponse pricing,
        String status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}