package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.account.AccountStatus;

public record UpdateAccountStatusCommand(
    String targetId,
    AccountStatus newStatus
) {
}
