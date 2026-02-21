package com.itravel.platform.modules.identity.application.command.auth;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;

public record SendOtpCommand(
        Email email
) {}
