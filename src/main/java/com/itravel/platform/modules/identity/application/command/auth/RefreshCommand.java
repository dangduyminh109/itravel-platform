package com.itravel.platform.modules.identity.application.command.auth;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;

public record RefreshCommand(
        String refreshToken,
        AccountId accountId
) {}
