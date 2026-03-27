package com.itravel.platform.modules.location.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateStatusLocationRequest(
        @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status
) { }
