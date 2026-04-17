package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;

@Builder
public record TourImageDTO(
        Long id,
        String imageUrl,
        Boolean isThumbnail
) {}