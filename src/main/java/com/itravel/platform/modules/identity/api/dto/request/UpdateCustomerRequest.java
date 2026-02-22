package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCustomerRequest(
        @NotBlank(message = "FULL_NAME_CANNOT_BE_BLANK")
        String fullName,
        String newPassword
) { }
