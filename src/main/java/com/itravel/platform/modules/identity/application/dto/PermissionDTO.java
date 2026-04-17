package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;

@Builder
public record PermissionDTO(
        String code,
        String description,
        String group
) {}
