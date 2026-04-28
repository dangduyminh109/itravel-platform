package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Builder
public record ScheduleDetailDTO(
    Long id,
    LocalDateTime departureDate,
    BigDecimal surcharge,
    Integer availableSeats,
    Integer totalSeats,
    Integer bookedSeats,
    Integer lockedSeats,
    PricingDTO pricing,
    String status,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
){}
