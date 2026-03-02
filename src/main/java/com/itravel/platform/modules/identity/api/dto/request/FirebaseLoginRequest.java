package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FirebaseLoginRequest(
        @NotBlank(message = "USERNAME_CANNOT_BE_BLANK")
        String idToken
) {}

