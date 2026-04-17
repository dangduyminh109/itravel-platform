package com.itravel.platform.modules.identity.application.port.in.customer;
import com.itravel.platform.modules.identity.application.command.model.customer.DeleteCustomerCommand;
public interface DeleteCustomerUseCase { void execute(DeleteCustomerCommand command); }
