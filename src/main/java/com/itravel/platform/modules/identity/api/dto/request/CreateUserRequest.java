package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record CreateUserRequest(
        @NotBlank(message = "USERNAME_CANNOT_BE_BLANK")
        String username,

        @NotBlank(message = "PASSWORD_CANNOT_BE_BLANK")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "PASSWORD_INVALID")
        String password,

        @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
        String fullName,

        @NotEmpty(message = "ROLE_LIST_CANNOT_BE_EMPTY")
        Set<@NotNull(message = "ROLE_ID_CANNOT_BE_NULL") Long> roleList,

        Set<@Valid PermissionOverrideRequest> permissionOverrides
) { }

