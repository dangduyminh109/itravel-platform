package com.itravel.platform.modules.identity.application.command.model.auth;

import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.user.FullName;
import com.itravel.platform.modules.identity.domain.otp.OtpCode;
import com.itravel.platform.modules.identity.domain.account.RawPassword;

public record RegisterCustomerByEmailCommand(
        FullName fullName,
        Email email,
        RawPassword password,
        OtpCode otp
) {}
