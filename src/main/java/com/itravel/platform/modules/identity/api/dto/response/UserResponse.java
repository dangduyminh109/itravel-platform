package com.itravel.platform.modules.identity.api.dto.response;

import com.itravel.platform.modules.identity.api.dto.request.PermissionOverrideRequest;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public record UserResponse(
        String id,
        String username,
        String fullName,
        String phoneNumber,
        String avatar,
        String gender,
        String email,
        LocalDate dateOfBirth,
        Set<RoleResponse> roleList,
        Set<PermissionOverrideRequest> permissionOverrides,
        AccountStatus status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
