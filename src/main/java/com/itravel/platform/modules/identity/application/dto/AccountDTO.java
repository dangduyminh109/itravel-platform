package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;
import java.time.Instant;
import java.util.Set;

@Builder
public record AccountDTO(
        String id,
        String username,
        String email,
        String status,
        Set<RoleDTO> roleList,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
