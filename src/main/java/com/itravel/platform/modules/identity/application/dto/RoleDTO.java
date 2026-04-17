package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;
import java.util.Set;

@Builder
public record RoleDTO(
        Long id,
        String name,
        String status,
        Set<String> permissionList
) {}
