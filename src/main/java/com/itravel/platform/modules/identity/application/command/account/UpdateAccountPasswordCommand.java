package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public record UpdateAccountPasswordCommand(
    String targetId,
    PasswordHash newPassword
) {
}
