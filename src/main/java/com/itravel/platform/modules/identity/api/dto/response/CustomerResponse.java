package com.itravel.platform.modules.identity.api.dto.response;

import java.time.Instant;
import java.util.Set;

public record CustomerResponse(
        String id,
        String fullName,
        String email,
        Set<Long> roleList,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
