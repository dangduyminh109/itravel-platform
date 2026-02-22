package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateRoleRequest(
        @NotBlank(message = "NAME_CANNOT_BE_BLANK")
        String name,

        @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status
) {}
