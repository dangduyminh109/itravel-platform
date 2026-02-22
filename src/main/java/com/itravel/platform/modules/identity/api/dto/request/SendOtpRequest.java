package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SendOtpRequest(
        @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
        @Email(message = "EMAIL_INVALID")
        String email
) {}
