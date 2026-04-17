package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.domain.account.Account;

public interface RegisterCustomerByGoogleUseCase {
    Account execute(RegisterCustomerByGoogleCommand command);
}
