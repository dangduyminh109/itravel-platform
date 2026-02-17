package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public record RegisterCustomerByEmailCommand(
        FullName fullName,
        Email email,
        PasswordHash password
) {}
