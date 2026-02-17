package com.itravel.platform.modules.identity.api.dto.response;

import java.time.Instant;
import java.util.Set;

public record UserResponse(
        String id,
        String username,
        String fullName,
        Set<Long> roleList,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
