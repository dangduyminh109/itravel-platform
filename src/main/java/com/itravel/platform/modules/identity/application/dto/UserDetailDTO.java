package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record UserDetailDTO(
    String id,
    String username,
    String fullName,
    String phoneNumber,
    String avatar,
    String gender,
    String email,
    LocalDate dateOfBirth,
    Set<RoleDTO> roleList,
    Set<PermissionOverrideDTO> permissionOverrides,
    String status,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {}

