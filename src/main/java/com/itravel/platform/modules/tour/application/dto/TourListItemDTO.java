package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;

@Builder
public record TourListItemDTO(
        String id,
        String name,
        String slug,
        String thumbnailUrl,
        PricingDTO pricing,
        String categoryName,
        String status
) {}

