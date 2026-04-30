package com.itravel.platform.modules.tour.api.dto.response;

import java.time.Instant;
import java.util.List;

public record TourDetailResponse(
        String id,
        String name,
        String slug,
        String summary,
        String description,
        String status,
        PricingResponse pricing,
        Integer durationDays,
        Integer durationNights,
        Integer minParticipants,
        Integer maxParticipants,
        List<String> includedServices,
        List<String> excludedServices,
        String categoryName,
        String departureLocationName,
        String destinationLocationName,
        List<ItineraryResponse> itineraries,
        List<TourImageResponse> tourImages,
        Instant createdAt,
        Instant updatedAt
) {}