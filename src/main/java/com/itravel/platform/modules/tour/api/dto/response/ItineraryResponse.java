package com.itravel.platform.modules.tour.api.dto.response;

import java.util.List;

public record ItineraryResponse(
        Long id,
        Integer dayNumber,
        String title,
        String description,
        List<String> activities
) {}