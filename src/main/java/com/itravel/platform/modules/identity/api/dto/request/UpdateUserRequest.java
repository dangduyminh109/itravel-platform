package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record UpdateUserRequest(
        String newPassword,

        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status,

        @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
        String fullName,

        @NotEmpty(message = "ROLE_LIST_CANNOT_BE_EMPTY")
        Set<@NotNull(message = "ROLE_ID_CANNOT_BE_NULL") Long> roleList,

        Set<@Valid PermissionOverrideRequest> permissionOverrides
) { }
