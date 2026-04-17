package com.itravel.platform.modules.identity.application.port.in.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;

public interface RegisterCustomerByEmailUseCase {
    CustomerDetailDTO execute(RegisterCustomerByEmailCommand command);
}
