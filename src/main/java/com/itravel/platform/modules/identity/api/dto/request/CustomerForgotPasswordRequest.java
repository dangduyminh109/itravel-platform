package com.itravel.platform.modules.identity.api.dto.request;

public record CustomerForgotPasswordRequest(
   String email,
   String newPassword,
   String confirmPassword,
   String otp
) {}
