package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ItineraryDTO(
        Long id,
        Integer dayNumber,
        String title,
        String description,
        List<String> activities
) {}

