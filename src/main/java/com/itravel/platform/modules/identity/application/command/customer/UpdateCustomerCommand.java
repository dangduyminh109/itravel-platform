package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public record UpdateCustomerCommand(
        CustomerId id,
        FullName fullName,
        PasswordHash newPassword
) { }
