package com.itravel.platform.modules.identity.api.dto.response;

import com.itravel.platform.modules.identity.api.dto.request.PermissionOverrideRequest;

import java.time.Instant;
import java.util.Set;

public record UserResponse(
        String id,
        String username,
        String fullName,
        Set<Long> roleList,
        Set<PermissionOverrideRequest> permissionOverrides,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
