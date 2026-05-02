package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Builder
public record ScheduleDetailDTO(
    String id,
    LocalDate departureDate,
    BigDecimal surcharge,
    Integer availableSeats,
    Integer totalSeats,
    Integer bookedSeats,
    Integer lockedSeats,
    PricingDTO pricing,
    String status,
    String tourId,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
){}
