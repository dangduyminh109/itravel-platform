package com.itravel.platform.modules.identity.application.port.in.customer;
import com.itravel.platform.modules.identity.application.command.model.customer.UpdateCustomerCommand;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
public interface UpdateCustomerUseCase { CustomerDetailDTO execute(UpdateCustomerCommand command); }
