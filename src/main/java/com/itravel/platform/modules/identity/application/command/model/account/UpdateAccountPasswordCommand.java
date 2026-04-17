package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.account.RawPassword;

public record UpdateAccountPasswordCommand(
    String targetId,
    RawPassword newPassword
) {
}
