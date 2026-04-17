package com.itravel.platform.modules.identity.application.command.model.auth;

import com.itravel.platform.modules.identity.domain.user.Email;

public record SendOtpCommand(
        Email email
) {}
