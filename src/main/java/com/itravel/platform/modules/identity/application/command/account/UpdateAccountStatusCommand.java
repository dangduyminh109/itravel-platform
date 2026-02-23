package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;

public record UpdateAccountStatusCommand(
    String targetId,
    AccountStatus newStatus
) {
}
