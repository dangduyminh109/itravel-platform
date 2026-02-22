package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdatePermissionsForRoleRequest(
        @NotNull(message = "ROLE_ID_CANNOT_BE_NULL")
        Long id,

        @NotEmpty(message = "PERMISSION_CODE_LIST_CANNOT_BE_EMPTY")
        List<String> permissionCodeList
) {}
