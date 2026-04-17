package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;

@Builder
public record PermissionOverrideDTO(
        String permission,
        String permissionType
) {}
