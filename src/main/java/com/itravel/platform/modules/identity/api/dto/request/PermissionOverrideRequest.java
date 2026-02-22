package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PermissionOverrideRequest(
    @NotBlank(message = "PERMISSION_CANNOT_BE_BLANK")
    String permission,

    @NotBlank(message = "PERMISSION_TYPE_CANNOT_BE_BLANK")
    @Pattern(regexp = "^$|^(GRANT|DENY)$", message = "PERMISSION_TYPE_INVALID")
    String permissionType
){}
