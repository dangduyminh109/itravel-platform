package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;

public record UpdateCustomerCommand(
        CustomerId id,
        FullName fullName
) { }
