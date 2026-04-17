package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateCategoryRequest(
        @NotBlank(message = "NAME_CANNOT_BE_BLANK")
        String name,

        String description,

        @NotBlank(message = "STATUS_CANNOT_BE_BLANK")
        @Pattern(regexp = "^$|^(ACTIVE|INACTIVE)$", message = "STATUS_INVALID")
        String status
) { }
