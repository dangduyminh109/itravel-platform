package com.itravel.platform.modules.identity.application.command.model.customer;

import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.user.FullName;
import com.itravel.platform.modules.identity.domain.account.ProviderId;

public record RegisterCustomerByGoogleCommand(Email email, FullName fullName, ProviderId providerId) {}
