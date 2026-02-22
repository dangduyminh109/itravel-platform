package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "IDENTIFIER_CANNOT_BE_BLANK")
        String identifier,

        @NotBlank(message = "PASSWORD_CANNOT_BE_BLANK")
        String password
) {}
