package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(
        @NotBlank(message = "REFRESH_TOKEN_CANNOT_BE_BLANK")
        String refreshToken
) {}
