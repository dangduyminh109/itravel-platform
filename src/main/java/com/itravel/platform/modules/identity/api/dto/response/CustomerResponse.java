package com.itravel.platform.modules.identity.api.dto.response;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;

import java.time.Instant;
import java.util.Set;

public record CustomerResponse(
        String id,
        String fullName,
        String email,
        Set<Long> roleList,
        AccountStatus status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
