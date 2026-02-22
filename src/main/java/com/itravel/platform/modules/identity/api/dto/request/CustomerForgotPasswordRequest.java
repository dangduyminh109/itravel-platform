package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerForgotPasswordRequest(
        @NotBlank(message = "EMAIL_CANNOT_BE_BLANK")
        @Email(message = "EMAIL_INVALID")
        String email,

        @NotBlank(message = "NEW_PASSWORD_CANNOT_BE_BLANK")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "NEW_PASSWORD_INVALID")
        String newPassword,

        @NotBlank(message = "CONFIRM_PASSWORD_CANNOT_BE_BLANK")
        String confirmPassword,

        @NotBlank(message = "OTP_CANNOT_BE_BLANK")
        @Pattern(regexp = "^[0-9]{6}$", message = "OTP_INVALID")
        String otp
) {}
