package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.domain.customer.*;


public record CreateAccountByGoogleCommand(
        Email email,
        CustomerId customerId,
        ProviderId providerId
) {}
