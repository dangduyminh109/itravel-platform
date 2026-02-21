package com.itravel.platform.modules.identity.application.command.auth;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public record CustomerForgotPasswordCommand(
        Email email,
        PasswordHash newPassword,
        PasswordHash confirmPassword,
        OtpCode otp
) {}
