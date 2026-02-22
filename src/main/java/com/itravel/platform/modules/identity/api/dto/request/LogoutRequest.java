package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
        @NotBlank(message = "ACCOUNT_ID_CANNOT_BE_BLANK")
        String accountId,

        @NotBlank(message = "REFRESH_TOKEN_CANNOT_BE_BLANK")
        String refreshToken
) {}