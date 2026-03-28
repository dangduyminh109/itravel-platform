package com.itravel.platform.modules.tour.api.dto.response;

import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import java.time.Instant;

public record CategoryResponse(
        Long id,
        String name,
        CategoryStatus status,
        String slug,
        String description,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
