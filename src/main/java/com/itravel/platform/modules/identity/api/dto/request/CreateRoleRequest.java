package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateRoleRequest(
        @NotBlank(message = "NAME_CANNOT_BE_BLANK")
        String name,

        @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status,

        @NotEmpty(message = "PERMISSION_CODE_LIST_CANNOT_BE_EMPTY")
        List<String> permissionCodeList
) {
}
