package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;
import java.time.Instant;
import java.util.List;

@Builder
public record TourDTO(
        String id,
        String name,
        String slug,
        String summary,
        String description,
        String status,
        PricingDTO pricing,
        DurationDTO duration,
        ParticipantLimitDTO participantLimit,
        ServicesDTO services,
        Long categoryId,
        Long departureLocationId,
        Long destinationLocationId,
        List<ItineraryDTO> itineraries,
        List<TourImageDTO> tourImages,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
){}
