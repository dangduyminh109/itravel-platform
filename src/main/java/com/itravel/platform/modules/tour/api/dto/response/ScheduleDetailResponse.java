package com.itravel.platform.modules.tour.api.dto.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record ScheduleDetailResponse(
        String id,
        LocalDate departureDate,
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