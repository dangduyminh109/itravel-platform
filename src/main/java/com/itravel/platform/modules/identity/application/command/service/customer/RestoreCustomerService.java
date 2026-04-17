package com.itravel.platform.modules.identity.application.command.service.customer;
import com.itravel.platform.modules.identity.application.command.model.account.RestoreAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.customer.RestoreCustomerUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestoreCustomerService implements RestoreCustomerUseCase {
    CustomerRepository customerRepository;
    AccountCommandFacade accountCommandHandler;
    
    @Transactional
    public void execute(RestoreCustomerCommand command){
        Customer customer = customerRepository.findById(command.id())
                .orElseThrow(CustomerNotExistException::new);
        accountCommandHandler.restore(new RestoreAccountCommand(command.id().value()));
        customer.restore();
        customerRepository.save(customer);
    }
}

