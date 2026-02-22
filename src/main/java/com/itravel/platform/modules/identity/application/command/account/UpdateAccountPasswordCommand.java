package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RawPassword;

public record UpdateAccountPasswordCommand(
    String targetId,
    RawPassword newPassword
) {
}
