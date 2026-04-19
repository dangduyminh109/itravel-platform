package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record TourDetailDTO(
        String id,
        String name,
        String slug,
        String summary,
        String description,
        String status,
        PricingDTO pricing,
        Integer durationDays,
        Integer durationNights,
        Integer minParticipants,
        Integer maxParticipants,
        List<String> includedServices,
        List<String> excludedServices,
        String categoryName,
        String departureLocationName,
        String destinationLocationName,
        List<ItineraryDTO> itineraries,
        List<TourImageDTO> tourImages,
        Instant createdAt,
        Instant updatedAt
) {}