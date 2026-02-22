package com.itravel.platform.modules.identity.application.command.customer;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RawPassword;

public record CustomerCreateCommand(
        FullName fullName,
        Email email,
        RawPassword password
) {}
