package com.itravel.platform.modules.identity.application.port.in.customer.facade;
import com.itravel.platform.modules.identity.application.command.model.customer.*;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.itravel.platform.modules.identity.application.port.in.customer.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerCommandFacade {
    CreateCustomerUseCase createCustomerUseCase;
    UpdateCustomerUseCase updateCustomerUseCase;
    DeleteCustomerUseCase deleteCustomerUseCase;
    RestoreCustomerUseCase restoreCustomerUseCase;
    DestroyCustomerUseCase destroyCustomerUseCase;
    public CustomerDetailDTO create(CustomerCreateCommand command) { return createCustomerUseCase.execute(command); }
    public CustomerDetailDTO update(UpdateCustomerCommand command) { return updateCustomerUseCase.execute(command); }
    public void delete(DeleteCustomerCommand command) { deleteCustomerUseCase.execute(command); }
    public void restore(RestoreCustomerCommand command) { restoreCustomerUseCase.execute(command); }
    public void destroy(DeleteCustomerCommand command) { destroyCustomerUseCase.execute(command); }
}

