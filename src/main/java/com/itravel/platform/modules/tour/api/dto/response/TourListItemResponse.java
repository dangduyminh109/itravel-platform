package com.itravel.platform.modules.tour.api.dto.response;

import java.math.BigDecimal;

public record TourListItemResponse(
        String id,
        String name,
        String slug,
        String thumbnailUrl,
        PricingResponse pricing,
        String categoryName,
        String status
) {}

