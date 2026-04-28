package com.itravel.platform.modules.tour.api.dto.response;

public record TourListItemResponse(
        String id,
        String name,
        String slug,
        String thumbnailUrl,
        PricingResponse pricing,
        String departureLocation,
        String destinationLocation,
        String categoryName,
        String status
) {}

