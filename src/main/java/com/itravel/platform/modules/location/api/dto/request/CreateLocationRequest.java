package com.itravel.platform.modules.location.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateLocationRequest(
        @NotBlank(message = "NAME_CANNOT_BE_BLANK")
        String name,

        String parentId,

        @NotBlank(message = "TYPE_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(AREA|REGION|PROVINCE|DESTINATION)$", message = "STATUS_INVALID")
        String type,

        @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status
) { }
