package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;

public record CreateAccountByGoogleCommand(
        Email email,
        CustomerId customerId
) {}
