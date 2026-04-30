package com.itravel.platform.modules.tour.api.dto.response;

import java.time.Instant;
import java.util.List;

public record TourResponse(
        String id,
        String name,
        String slug,
        String summary,
        String description,
        String status,
        PricingResponse pricing,
        DurationResponse duration,
        ParticipantLimitResponse participantLimit,
        ServicesResponse services,
        Long categoryId,
        Long departureLocationId,
        Long destinationLocationId,
        List<ItineraryResponse> itineraries,
        List<TourImageResponse> tourImages,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}