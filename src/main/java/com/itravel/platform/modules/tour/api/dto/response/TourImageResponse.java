package com.itravel.platform.modules.tour.api.dto.response;

public record TourImageResponse(
        Long id,
        String imageUrl,
        Boolean isThumbnail
) {}

