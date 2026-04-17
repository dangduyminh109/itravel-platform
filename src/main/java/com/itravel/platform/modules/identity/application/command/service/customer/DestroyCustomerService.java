package com.itravel.platform.modules.identity.application.command.service.customer;
import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.*;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.customer.DestroyCustomerUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyCustomerService implements DestroyCustomerUseCase {
    CustomerRepository customerRepository;
    AccountCommandFacade accountCommandHandler;
    
    @Transactional
    public void execute(DeleteCustomerCommand command){
        accountCommandHandler.destroy(new DeleteAccountCommand(command.id().value()));
        customerRepository.destroy(command.id());
    }
}

