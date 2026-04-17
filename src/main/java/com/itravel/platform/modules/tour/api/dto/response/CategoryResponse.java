package com.itravel.platform.modules.tour.api.dto.response;

import java.time.Instant;

public record CategoryResponse(
        Long id,
        String name,
        String status,
        String slug,
        String description,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
