package com.itravel.platform.modules.tour.application.dto;

import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record TourListItemDTO(
        String id,
        String name,
        String slug,
        String thumbnailUrl,
        BigDecimal originalPrice,
        BigDecimal discountPrice,
        String categoryName,
        String status
) {}

