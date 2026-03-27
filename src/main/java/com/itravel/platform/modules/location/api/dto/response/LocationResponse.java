package com.itravel.platform.modules.location.api.dto.response;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationType;

import java.time.Instant;
import java.util.List;

public record LocationResponse(
        Long id,
        String name,
        LocationType type,
        LocationStatus status,
        String slug,
        LocationResponse parent,
        List<LocationResponse> children,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
