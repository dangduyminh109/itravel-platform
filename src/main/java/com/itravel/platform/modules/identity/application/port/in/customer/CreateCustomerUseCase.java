package com.itravel.platform.modules.identity.application.port.in.customer;
import com.itravel.platform.modules.identity.application.command.model.customer.CustomerCreateCommand;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;

public interface CreateCustomerUseCase {
    CustomerDetailDTO execute(CustomerCreateCommand command);
}
