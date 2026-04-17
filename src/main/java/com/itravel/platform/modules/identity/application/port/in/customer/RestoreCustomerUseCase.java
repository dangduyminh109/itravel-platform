package com.itravel.platform.modules.identity.application.port.in.customer;
import com.itravel.platform.modules.identity.application.command.model.customer.RestoreCustomerCommand;
public interface RestoreCustomerUseCase { void execute(RestoreCustomerCommand command); }
